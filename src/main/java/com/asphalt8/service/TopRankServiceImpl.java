package com.asphalt8.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asphalt8.comparator.ComparatorFactory;
import com.asphalt8.entity.CarFeature;

@Component("topRankServiceImpl")
public class TopRankServiceImpl extends RedisService implements TopRankService {

	@Autowired
	private CarFeatureService featureService;

	@Autowired
	private ComparatorFactory comparatorFactory;

	private List<CarFeature> fs;

	private final int topN = 10;

	private String topNitroSpeedKey = "topNitroSpeed";
	private String topNitroKey = "topNitro";
	private String topSpeedKey = "topSpeed";
	private String topAccelerationKey = "topAcceleration";

	private static Logger logger = Logger.getLogger(TopRankServiceImpl.class);

	@Override
	public List<CarFeature> queryTopNitroSpeed() {

		List<CarFeature> sortByNitroSpeed = null;
		if (redisDao.isRedisUp()) {
			sortByNitroSpeed = redisDao.queryTopNitro();
		}

		if (sortByNitroSpeed == null || sortByNitroSpeed.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No top data from redis: " + topNitroSpeedKey);
				logger.debug("Query data from car feature service ...");
			}

			if (fs == null) {
				fs = featureService.queryAllCar();
			}

			Collections.sort(fs, comparatorFactory.getNitroSpeedComparator());

			sortByNitroSpeed = new ArrayList<CarFeature>(topN);

			try {
				for (int i = 0; i < topN; i++) {
					CarFeature o = fs.get(i);
					sortByNitroSpeed.add((CarFeature) o.clone());
				}
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				sortByNitroSpeed.clear();
				sortByNitroSpeed = new ArrayList<CarFeature>(
						fs.subList(0, topN));
			}

			if (redisDao.isRedisUp()) {

				if (logger.isDebugEnabled()) {
					logger.debug("Multi thread to save top nitro speed to redis ...");
				}

				SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
						topNitroSpeedKey, sortByNitroSpeed);
				Thread t = new Thread(saveToRedis);
				t.start();
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Top 10 nitro speeds: ");
				for (int i = 0; i < sortByNitroSpeed.size(); i++) {
					CarFeature f = sortByNitroSpeed.get(i);
					logger.debug(i + ". " + f.getCarName() + ": "
							+ f.getMaxNitroSpeed());
				}
			}
		}

		return sortByNitroSpeed;
	}

	@Override
	public List<CarFeature> queryTopNitro() {

		List<CarFeature> sortByNitro = null;
		if (redisDao.isRedisUp()) {
			sortByNitro = redisDao.queryTopNitro();
		}

		if (sortByNitro == null || sortByNitro.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No top data from redis: " + topNitroKey);
				logger.debug("Query data from car feature service ...");
			}

			if (fs == null) {
				fs = featureService.queryAllCar();
			}

			Collections.sort(fs, comparatorFactory.getNitroComparator());

			sortByNitro = new ArrayList<CarFeature>(topN);

			try {
				for (int i = 0; i < topN; i++) {
					CarFeature o = fs.get(i);
					sortByNitro.add((CarFeature) o.clone());
				}
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				sortByNitro.clear();
				sortByNitro = new ArrayList<CarFeature>(fs.subList(0, topN));
			}

			if (redisDao.isRedisUp()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Multi thread to save top nitro to redis ...");
				}
				SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
						topNitroKey, sortByNitro);
				Thread t = new Thread(saveToRedis);
				t.start();
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Top 10 nitro: ");
				for (int i = 0; i < sortByNitro.size(); i++) {
					CarFeature f = sortByNitro.get(i);
					logger.debug(i + ". " + f.getCarName() + ": "
							+ f.getMaxNitro());
				}
			}
		}

		return sortByNitro;
	}

	@Override
	public List<CarFeature> queryTopHandling() {

		List<CarFeature> sortByHandling = null;
		if (redisDao.isRedisUp()) {
			sortByHandling = redisDao.queryTopHandling();
		}

		if (sortByHandling == null || sortByHandling.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No top data from redis: " + topNitroKey);
				logger.debug("Query data from car feature service ...");
			}

			if (fs == null) {
				fs = featureService.queryAllCar();
			}

			Collections.sort(fs, comparatorFactory.getHandlingComparator());

			sortByHandling = new ArrayList<CarFeature>(topN);

			try {
				for (int i = 0; i < topN; i++) {
					CarFeature o = fs.get(i);
					sortByHandling.add((CarFeature) o.clone());
				}
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				sortByHandling.clear();
				sortByHandling = new ArrayList<CarFeature>(fs.subList(0, topN));
			}

			if (redisDao.isRedisUp()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Multi thread to save top nitro to redis ...");
				}
				SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
						topNitroKey, sortByHandling);
				Thread t = new Thread(saveToRedis);
				t.start();
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Top 10 handling: ");
				for (int i = 0; i < sortByHandling.size(); i++) {
					CarFeature f = sortByHandling.get(i);
					logger.debug(i + ". " + f.getCarName() + ": "
							+ f.getMaxHandling());
				}
			}
		}

		return sortByHandling;

	}

	@Override
	public List<CarFeature> queryTopSpeed() {

		List<CarFeature> sortBySpeed = null;
		if (redisDao.isRedisUp()) {
			sortBySpeed = redisDao.queryTopSpeed();
		}

		if (sortBySpeed == null || sortBySpeed.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No top data from redis: " + topNitroKey);
				logger.debug("Query data from car feature service ...");
			}

			if (fs == null) {
				fs = featureService.queryAllCar();
			}

			Collections.sort(fs, comparatorFactory.getSpeedComparator());
			sortBySpeed = new ArrayList<CarFeature>(topN);

			try {
				for (int i = 0; i < topN; i++) {
					CarFeature o = fs.get(i);
					sortBySpeed.add((CarFeature) o.clone());
				}
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				sortBySpeed.clear();
				sortBySpeed = new ArrayList<CarFeature>(fs.subList(0, topN));
			}

			if (redisDao.isRedisUp()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Multi thread to save top nitro to redis ...");
				}
				SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
						topSpeedKey, sortBySpeed);
				Thread t = new Thread(saveToRedis);
				t.start();
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Top 10 handling: ");
				for (int i = 0; i < sortBySpeed.size(); i++) {
					CarFeature f = sortBySpeed.get(i);
					logger.debug(i + ". " + f.getCarName() + ": "
							+ f.getMaxSpeed());
				}
			}
		}

		return sortBySpeed;
	}

	@Override
	public List<CarFeature> queryTopAcceleration() {

		List<CarFeature> sortByAcceleration = null;

		if (redisDao.isRedisUp()) {
			sortByAcceleration = redisDao.queryTopAcceleration();
		}

		if (sortByAcceleration == null || sortByAcceleration.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No top data from redis: " + topAccelerationKey);
				logger.debug("Query data from car feature service ...");
			}

			if (fs == null) {
				fs = featureService.queryAllCar();
			}

			Collections.sort(fs, comparatorFactory.getAccelerationComparator());

			sortByAcceleration = new ArrayList<CarFeature>(topN);

			try {
				for (int i = 0; i < topN; i++) {
					CarFeature o = fs.get(i);
					sortByAcceleration.add((CarFeature) o.clone());
				}
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				sortByAcceleration.clear();
				sortByAcceleration = new ArrayList<CarFeature>(fs.subList(0,
						topN));
			}

			if (redisDao.isRedisUp()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Multi thread to save top nitro to redis ...");
				}
				SaveObjectToRedis<CarFeature> saveToRedis = new SaveObjectToRedis<CarFeature>(
						topAccelerationKey, sortByAcceleration);
				Thread t = new Thread(saveToRedis);
				t.start();
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Top 10 handling: ");
				for (int i = 0; i < sortByAcceleration.size(); i++) {
					CarFeature f = sortByAcceleration.get(i);
					logger.debug(i + ". " + f.getCarName() + ": "
							+ f.getMaxAcceleration());
				}
			}
		}

		return sortByAcceleration;
	}
}
