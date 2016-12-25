package com.asphalt8.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asphalt8.dao.RedisDao;
import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarFeature;
import com.asphalt8.entity.CarInfo;
import com.asphalt8.entity.CarIntroduction;
import com.asphalt8.entity.CarMedia;
import com.asphalt8.entity.CarStat;

@Component("redisService")
public class RedisService {

	@Resource
	protected RedisDao redisDao;

	private static Logger logger = Logger.getLogger(RedisService.class);

	public FlushRedis getFlushRedisObject() {
		return new FlushRedis();
	}

	class SaveObjectToRedis<T> implements Runnable {

		private T obj;
		private Collection<T> objs;
		private String key;

		public SaveObjectToRedis(T t) {
			this.obj = t;
		}

		public SaveObjectToRedis(String k, T t) {
			this.key = k;
			this.obj = t;
		}

		public SaveObjectToRedis(Collection<T> c) {
			this.objs = c;
		};

		public SaveObjectToRedis(String k, Collection<T> c) {
			this.key = k;
			this.objs = c;

			logger.debug("key => " + this.key);
		};

		@SuppressWarnings("unchecked")
		@Override
		public void run() {

			String threadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(threadName + " running...");
				logger.debug("key => " + key);
			}

			if (obj != null) {

				if (obj instanceof Car) {
					redisDao.save((Car) obj);
				} else if (obj instanceof CarFeature) {
					redisDao.save((CarFeature) obj);
				} else if (obj instanceof CarStat) {
					redisDao.save((CarStat) obj);
				} else if (obj instanceof CarInfo) {
					redisDao.save((CarInfo) obj);
				} else if (obj instanceof CarIntroduction) {
					redisDao.save((CarIntroduction) obj);
				}

			}

			if (objs != null && objs.size() > 0) {

				Iterator<T> it = objs.iterator();
				T o = it.next();
				if (o instanceof CarMedia) {

					if (key == null) {
						List<CarMedia> list = (List<CarMedia>) objs;
						redisDao.save(list);
					} else {
						if (key.contains("all")) {
							List<CarMedia> medias = (List<CarMedia>) objs;
							redisDao.saveAllCars(medias);
						} else {
							Set<CarMedia> medias = (HashSet<CarMedia>) objs;
							redisDao.save(key, medias);
						}
					}

				} else if (o instanceof Car) {
					List<Car> cars = (List<Car>) objs;
					redisDao.save(key, cars);
				} else if (o instanceof CarFeature) {
					if (key != null) {
						if (key.contains("all")) {
							List<CarFeature> list = (List<CarFeature>) objs;
							Set<CarFeature> set = new HashSet<CarFeature>();
							set.addAll(list);
							redisDao.saveAllCars(set);
						} else {
							redisDao.save((List<CarFeature>) objs, key);
						}
					}
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Multi Thead - " + threadName + " completed...");
			}

		}
	}

	public class FlushRedis implements Runnable {

		@Override
		public void run() {

			String threadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(threadName + " running...");
			}

			redisDao.flushRedis();

			if (logger.isDebugEnabled()) {
				logger.debug("Multi Thead - " + threadName + " completed...");
			}

		}

	}

}
