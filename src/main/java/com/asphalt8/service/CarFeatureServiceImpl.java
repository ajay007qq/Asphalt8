package com.asphalt8.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asphalt8.dao.CarFeatureDao;
import com.asphalt8.entity.CarFeature;

@Component("carFeatureServiceImpl")
public class CarFeatureServiceImpl extends RedisService implements
		CarFeatureService {

	@Resource
	private CarFeatureDao carFeatureDao;

	private final String allCarKey = "allCar";

	@Override
	public CarFeature queryByCarId(int id) {
		return carFeatureDao.queryByCarId(id);
	}

	@Override
	public int insertCarFeature(CarFeature feature) {
		return carFeatureDao.insertCarFeature(feature);
	}

	private static Logger logger = Logger
			.getLogger(CarFeatureServiceImpl.class);

	@Override
	public CarFeature queryByCarName(String name) {

		CarFeature feature = null;
		if (redisDao.isRedisUp()) {
			feature = redisDao.queryCarFeatureByName(name);
		}

		if (feature == null) {

			if (logger.isDebugEnabled()) {
				logger.debug("No feature from redis: " + name);
				logger.debug("Query data from database...");
			}

			feature = carFeatureDao.queryByCarName(name);

			if (feature != null) {

				if (logger.isDebugEnabled()) {
					logger.debug("Feature return from database: " + feature);
				}

				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save feature to redis ...");
					}
					SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
							feature);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {
				logger.warn("No data from database: " + name);
			}
		}

		return feature;
	}

	@Override
	public Integer save(CarFeature t) {
		return carFeatureDao.save(t);
	}

	@Override
	public List<CarFeature> queryAllCar() {

		List<CarFeature> fs = null;

		if (redisDao.isRedisUp()) {
			fs = redisDao.queryAllCarsFeature();
		}

		if (fs == null || fs.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No feature from redis");
				logger.debug("Query data from database...");
			}

			fs = carFeatureDao.queryAllCars();

			if (fs != null && fs.size() > 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("Feature return from database: " + fs.size());
				}

				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save feature to redis ...");
					}
					SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
							allCarKey, fs);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {
				logger.warn("No data from database");
			}
		}

		return fs;

	}
}
