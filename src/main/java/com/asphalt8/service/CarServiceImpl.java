package com.asphalt8.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.asphalt8.dao.CarDao;
import com.asphalt8.dao.RedisDao;
import com.asphalt8.entity.Car;
import com.asphalt8.exception.NoCarFoundException;

@Component("carServiceImpl")
public class CarServiceImpl implements CarService {

	@Resource
	private CarDao carDao;

	@Resource
	private RedisDao redisDao;

	private static Logger logger = Logger.getLogger(CarServiceImpl.class);

	@Override
	public Car queryByCarId(int id) {
		return carDao.queryByCarId(id);
	}

	@Override
	public Car queryByCarName(String name) throws NoCarFoundException {
		try {

			Car car = null;
			if (redisDao.isRedisUp()) {
				car = redisDao.queryByCarName(name);
			}

			if (car == null) {

				if (logger.isDebugEnabled()) {
					logger.debug("No car from redis ...");
					logger.debug("Query car from database ...");
				}

				car = carDao.queryByCarName(name);

				if (car == null) {
					throw new NoCarFoundException("没有查询到赛车信息");
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug("Car from data base: " + car);
						logger.debug("Multi thread to save car to redis ...");
					}
					/**
					 * 子线程保存数据到redis
					 */
					if (redisDao.isRedisUp()) {
						SaveCarToRedis saveToRedis = new SaveCarToRedis(car);
						Thread t = new Thread(saveToRedis);
						t.start();
					}
				}
			}

			return car;

		} catch (Exception e) {
			throw new NoCarFoundException("查询赛车信息发生异常： " + e);
		}
	}

	@Override
	public List<Car> queryCarByVendor(String vendor) {
		return carDao.queryByVendor(vendor);
	}

	@Override
	public List<Car> queryCarsForAdvertisment(String name) {
		return carDao.queryCarsForAdvertisment(name);
	}

	@Override
	public List<Car> queryAllCars() {
		return carDao.queryAllCars();
	}

	@Override
	public int insertCar(Car car) {
		return carDao.insertCar(car);
	}

	@Override
	public List<Car> queryCarByKeyWord(String keyword) {

		List<Car> cars = null;
		if (redisDao.isRedisUp()) {
			cars = redisDao.queryCarByKeyWord(keyword);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Return cars size: "
					+ (cars == null ? 0 : cars.size()));
		}

		if (cars == null || cars.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No data return from redis");
				logger.debug("Query from database");
			}

			cars = carDao.queryCarByKeyWord(keyword);

			if (cars == null || cars.size() == 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("No data return from database for keyword :"
							+ keyword);
				}

			} else {

				if (logger.isDebugEnabled()) {
					logger.debug("Found " + cars.size() + " cars in database");
				}
				/**
				 * 子线程保存数据到redis
				 */
				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save medias to redis ...");
					}
					SaveCarsToRedis saveToRedis = new SaveCarsToRedis(keyword,
							cars);
					Thread t = new Thread(saveToRedis);
					t.start();
				}
			}
		}

		return cars;
	}

	@Override
	public List<String> queryVendorsByKeyWord(String keyword) {
		return carDao.queryVendorsByKeyWord(keyword);
	}

	@Override
	public Integer save(Car t) {
		if (StringUtils.isEmpty(t.getCarName())
				|| StringUtils.isEmpty(t.getVendor()))
			return 0;
		return carDao.save(t);
	}

	@Override
	public List<String> queryCarNameByKeyWord(String word) {

		List<Car> cars = null;
		if (redisDao.isRedisUp()) {
			cars = redisDao.queryCarByKeyWord(word);
		}

		List<String> carNames = null;

		if (cars == null || cars.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No data return from redis");
				logger.debug("Query from database");
			}

			carNames = carDao.queryCarNameByKeyWord(word);

			if (carNames == null || carNames.size() == 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("No car return from database for keyword :"
							+ word);
				}

			} else {

				if (redisDao.isRedisUp()) {

					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save result to redis ...");
					}

					/**
					 * 子线程保存数据到redis
					 */
					SaveCarsToRedis saveToRedis = new SaveCarsToRedis(word);
					Thread t = new Thread(saveToRedis);
					t.start();
				}
			}

		} else {

			if (logger.isDebugEnabled()) {
				logger.debug("Cars return from redis: " + cars.size());
			}

			carNames = new ArrayList<String>();
			for (Car c : cars) {
				carNames.add(c.getCarName());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Result: " + carNames);
			}
		}
		return carNames;
	}

	/**
	 * 内部类操作redis保存数据
	 * 
	 * @author Administrator
	 *
	 */

	class SaveCarsToRedis implements Runnable {

		private String wd;
		private List<Car> cars;

		public SaveCarsToRedis(String w) {
			this.wd = w;
		}

		public SaveCarsToRedis(String w, List<Car> c) {
			this.wd = w;
			this.cars = c;
		}

		@Override
		public void run() {

			String threadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(threadName + " running...");
				logger.debug("Query word: " + wd + " from redis");

			}

			if (cars == null) {
				cars = carDao.queryCarByKeyWord(wd);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Return cars list size: " + cars.size());
				logger.debug("Save cars to redis: " + cars.size());
			}

			if (cars != null && cars.size() > 0) {
				redisDao.save(wd, cars);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Multi Thead - " + threadName + " completed...");
			}
		}
	}

	class SaveCarToRedis implements Runnable {

		private Car car;

		public SaveCarToRedis(Car c) {
			this.car = c;
		}

		@Override
		public void run() {

			String threadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(threadName + " running...");
			}

			redisDao.save(car);

			if (logger.isDebugEnabled()) {
				logger.debug("Multi Thead - " + threadName + " completed...");
			}

		}
	}
}
