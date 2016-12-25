package com.asphalt8.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asphalt8.dao.CarIntroductionDao;
import com.asphalt8.dao.RedisDao;
import com.asphalt8.entity.CarIntroduction;

@Component("carIntroductionServiceImpl")
public class CarIntroductionServiceImpl implements CarIntroductionService {

	@Resource
	CarIntroductionDao carIntroductionDao;

	@Resource
	RedisDao redisDao;

	@Override
	public CarIntroduction queryByCarId(int id) {
		return carIntroductionDao.queryByCarId(id);
	}

	private static Logger logger = Logger
			.getLogger(CarIntroductionServiceImpl.class);

	@Override
	public CarIntroduction queryByCarName(String name) {

		CarIntroduction intro = null;
		if (redisDao.isRedisUp()) {
			intro = redisDao.queryCarIntroductionByName(name);
		}

		if (intro == null) {

			if (logger.isDebugEnabled()) {
				logger.debug("No introduction from redis: " + name);
				logger.debug("Query data from database...");
			}

			intro = carIntroductionDao.queryByCarName(name);

			if (intro != null) {

				if (logger.isDebugEnabled()) {
					logger.debug("Introduction return from database: " + intro);
					logger.debug("Multi thread to save introduction to redis ...");
				}

				if (redisDao.isRedisUp()) {
					SaveIntroToRedis saveToRedis = new SaveIntroToRedis(intro);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {
				logger.error("No introduction from database: " + name);
			}

		}

		return intro;
	}

	@Override
	public int insertCarIntroduction(CarIntroduction instroduction) {
		return carIntroductionDao.insertCarIntroduction(instroduction);
	}

	@Override
	public Integer save(CarIntroduction t) {
		return carIntroductionDao.save(t);
	}

	class SaveIntroToRedis implements Runnable {

		private CarIntroduction intro;

		public SaveIntroToRedis(CarIntroduction i) {
			this.intro = i;
		}

		@Override
		public void run() {

			String theadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(theadName + " running...");
			}

			redisDao.save(intro);

			if (logger.isDebugEnabled()) {
				logger.debug(theadName + " completed...");
			}

		}

	}

}
