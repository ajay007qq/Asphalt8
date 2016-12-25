package com.asphalt8.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asphalt8.dao.CarStatDao;
import com.asphalt8.dao.RedisDao;
import com.asphalt8.entity.CarStat;

@Component("carStatServiceImpl")
public class CarStatServiceImpl implements CarStatService {

	@Resource
	private CarStatDao carStatDao;

	@Resource
	private RedisDao redisDao;

	private static Logger logger = Logger.getLogger(CarStatServiceImpl.class);

	@Override
	public CarStat queryByCarId(int id) {
		return carStatDao.queryByCarId(id);
	}

	@Override
	public CarStat queryByCarName(String name) {

		CarStat stat = null;
		if (redisDao.isRedisUp()) {
			stat = redisDao.queryCarStatByName(name);
		}

		if (stat == null) {

			if (logger.isDebugEnabled()) {
				logger.debug("No stat from redis: " + name);
				logger.debug("Query data from database...");
			}

			stat = carStatDao.queryByCarName(name);

			if (stat != null) {

				if (logger.isDebugEnabled()) {
					logger.debug("Stat return from database: " + stat);
					logger.debug("Multi thread to save stat to redis ...");
				}

				if (redisDao.isRedisUp()) {
					SaveStatToRedis saveToRedis = new SaveStatToRedis(stat);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {

				if (logger.isDebugEnabled()) {
					logger.debug("No data from database: " + name);
				}

			}
		}

		return stat;
	}

	@Override
	public int insertCarStat(CarStat stat) {
		return carStatDao.insertCarStat(stat);
	}

	@Override
	public Integer save(CarStat t) {
		return carStatDao.save(t);
	}

	class SaveStatToRedis implements Runnable {

		private CarStat stat;

		public SaveStatToRedis(CarStat s) {
			this.stat = s;
		}

		@Override
		public void run() {

			String theadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(theadName + " running...");
			}

			redisDao.save(stat);

			if (logger.isDebugEnabled()) {
				logger.debug(theadName + " completed...");
			}

		}
	}

}
