package com.asphalt8.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asphalt8.dao.CarInfoDao;
import com.asphalt8.dao.RedisDao;
import com.asphalt8.entity.CarInfo;
import com.asphalt8.entity.CarMedia;
import com.asphalt8.exception.NoCarFoundException;

@Component("carInfoServiceImpl")
public class CarInfoServiceImpl implements CarInfoService {

	@Resource
	private CarInfoDao carInfoDao;

	@Resource
	private RedisDao redisDao;

	@Resource
	private CarFeatureService carFeatureService;

	@Resource
	CarIntroductionService CarIntroductionServices;

	@Resource
	CarStatService carStatService;

	@Resource
	CarMediaService carMediaService;

	private static Logger logger = Logger.getLogger(CarInfoServiceImpl.class);

	@Override
	public CarInfo queryByCarId(int id) throws NoCarFoundException {

		CarInfo carInfo = carInfoDao.queryByCarId(id);

		if (logger.isDebugEnabled()) {
			logger.debug("carInfo : " + carInfo);
		}

		if (carInfo == null) {
			throw new NoCarFoundException("没有查询到赛车信息：" + id);
		}

		String name = carInfo.getCar().getCarName();

		List<CarMedia> carImages = carMediaService.queryByName(name, "image");

		if (logger.isDebugEnabled()) {
			logger.debug("carImages : " + carImages);
		}

		if (carImages != null && carImages.size() > 0) {
			carInfo.setCarImages(carImages);
		} else {
			logger.warn("没有查询到赛车图片：" + name);
		}

		List<CarMedia> carVideo = carMediaService.queryByName(name, "video");

		if (logger.isDebugEnabled()) {
			logger.debug("carVideo : " + carVideo);
		}

		if (carVideo != null && carVideo.size() > 0) {
			carInfo.setCarVideo(carVideo.get(0));
		} else {
			logger.warn("没有查询到赛车视频：" + name);
		}

		return carInfo;
	}

	@Override
	public CarInfo queryByCarName(String name) throws NoCarFoundException {

		CarInfo carInfo = null;
		if (redisDao.isRedisUp()) {
			carInfo = redisDao.queryCarInfoByName(name);
		}

		if (carInfo == null) {

			if (logger.isDebugEnabled()) {
				logger.debug("No info from redis ...");
				logger.debug("Query car from database ...");
			}

			carInfo = carInfoDao.queryByCarName(name);

			if (carInfo == null) {
				throw new NoCarFoundException("没有查询到赛车信息：" + name);
			} else {

				if (logger.isDebugEnabled()) {
					logger.debug("Car info from data base: " + carInfo);
					logger.debug("Multi thread to save car info to redis ...");
				}

				if (redisDao.isRedisUp()) {
					SaveInfoToRedis saveToRedis = new SaveInfoToRedis(carInfo);
					Thread t = new Thread(saveToRedis);
					t.start();
				}
			}

		} else {

			if (logger.isDebugEnabled()) {
				logger.debug("Car info return from redis : " + carInfo);
			}

		}

		List<CarMedia> carImages = carMediaService.queryByName(name, "image");

		if (logger.isDebugEnabled()) {
			logger.debug("carImages : " + carImages);
		}

		if (carImages != null && carImages.size() > 0) {
			carInfo.setCarImages(carImages);
		} else {
			logger.warn("没有查询到赛车图片：" + name);
		}

		List<CarMedia> carVideo = carMediaService.queryByName(name, "video");

		if (logger.isDebugEnabled()) {
			logger.debug("carVideo : " + carVideo);
		}

		if (carVideo != null && carVideo.size() > 0) {
			carInfo.setCarVideo(carVideo.get(0));
		} else {
			logger.warn("没有查询到赛车视频：" + name);
		}

		return carInfo;
	}

	@Override
	public Integer save(CarInfo t) {
		return carInfoDao.save(t);
	}

	@Override
	public CarFeatureService getCarFeatureService() {
		return carFeatureService;
	}

	public void setCarFeatureService(CarFeatureService carFeatureService) {
		this.carFeatureService = carFeatureService;
	}

	@Override
	public CarIntroductionService getCarIntroductionServices() {
		return CarIntroductionServices;
	}

	public void setCarIntroductionServices(
			CarIntroductionService carIntroductionServices) {
		CarIntroductionServices = carIntroductionServices;
	}

	@Override
	public CarStatService getCarStatService() {
		return carStatService;
	}

	public void setCarStatService(CarStatService carStatService) {
		this.carStatService = carStatService;
	}

	@Override
	public CarMediaService getCarMediaService() {
		return carMediaService;
	}

	public void setCarMediaService(CarMediaService carMediaService) {
		this.carMediaService = carMediaService;
	}

	class SaveInfoToRedis implements Runnable {

		private CarInfo info;

		public SaveInfoToRedis(CarInfo i) {
			this.info = i;
		}

		@Override
		public void run() {

			String threadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(threadName + " running...");
			}

			redisDao.save(info);

			if (logger.isDebugEnabled()) {
				logger.debug("Multi Thead - " + threadName + " completed...");
			}

		}

	}

}
