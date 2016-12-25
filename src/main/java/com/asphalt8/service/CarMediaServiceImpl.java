package com.asphalt8.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.asphalt8.dao.CarMediaDao;
import com.asphalt8.dao.RedisDao;
import com.asphalt8.entity.CarMedia;
import com.asphalt8.exception.NoCarFoundException;

@Component("carMediaServiceImpl")
public class CarMediaServiceImpl implements CarMediaService {

	@Resource
	private CarMediaDao carMediaDao;

	@Resource
	private RedisDao redisDao;

	private String catetoryKey;
	private String allCarKey;

	public String getCatetoryKey() {
		return catetoryKey;
	}

	public void setCatetoryKey(String catetoryKey) {
		this.catetoryKey = catetoryKey;
	}

	public String getAllCarKey() {
		return allCarKey;
	}

	public void setAllCarKey(String allCarKey) {
		this.allCarKey = allCarKey;
	}

	private static Logger logger = Logger.getLogger(CarMediaServiceImpl.class);

	@Override
	public int queryNextImageNumById(int carId) {
		return carMediaDao.queryNextImageNumById(carId);
	}

	@Override
	public List<CarMedia> queryById(int carId, String type) {
		return carMediaDao.queryById(carId, type);
	}

	@Override
	public int insertCarMedia(CarMedia carMedia) {
		return carMediaDao.insertCarMedia(carMedia);
	}

	@Override
	public List<CarMedia> queryByName(String name, String type) {

		List<CarMedia> medias = null;
		if (redisDao.isRedisUp()) {
			medias = redisDao.queryCarMediaByName(name, type);
		}

		if (medias == null || medias.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No media from redis: " + name);
				logger.debug("Query data from database...");
			}

			medias = carMediaDao.queryByName(name, type);

			if (medias != null && medias.size() > 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("Media return from database: " + medias.size());
				}

				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save medias to redis ...");
					}
					SaveMediasToRedis saveToRedis = new SaveMediasToRedis(
							medias);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {

				if (logger.isDebugEnabled()) {
					logger.debug("No data from database: " + name);
				}

			}
		}

		return medias;
	}

	@Override
	public List<CarMedia> queryAllCars() throws NoCarFoundException {

		List<CarMedia> allCars = null;
		if (redisDao.isRedisUp()) {
			allCars = redisDao.queryAllCar();
		}

		if (allCars == null || allCars.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No car from redis for all key ");
				logger.debug("Query data from database...");
			}

			allCars = carMediaDao.queryAllCars();

			if (allCars != null && allCars.size() > 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("Media return from database: "
							+ allCars.size());
				}

				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save medias to redis ...");
					}
					SaveMediasToRedis saveToRedis = new SaveMediasToRedis(
							allCarKey, allCars);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {
				throw new NoCarFoundException("No car found from database");
			}
		}

		return allCars;
	}

	@Override
	public List<CarMedia> queryCarsByKeyword(String keyword) {

		List<CarMedia> medias = null;
		if (redisDao.isRedisUp()) {
			medias = redisDao.queryMediaByKeyword(keyword);
		}

		if (medias == null || medias.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No media from redis: " + keyword);
				logger.debug("Query data from database...");
			}

			medias = carMediaDao.queryCarsByKeyword(keyword);

			if (medias != null && medias.size() > 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("Media return from database: " + medias.size());
				}

				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save medias to redis ...");
					}
					SaveMediasToRedis saveToRedis = new SaveMediasToRedis(
							keyword, medias);
					Thread t = new Thread(saveToRedis);
					t.start();
				}

			} else {

				if (logger.isDebugEnabled()) {
					logger.debug("No data from database: " + keyword);
				}
			}
		}

		return medias;
	}

	@Override
	public List<CarMedia> queryCarsByCategory(String category) {

		List<CarMedia> medias = null;
		if (redisDao.isRedisUp()) {
			medias = redisDao.queryMediaByCatetory(category);
		}

		if (medias == null || medias.size() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("No media from redis: " + category);
				logger.debug("Query data from database...");
			}

			medias = carMediaDao.queryCarsByCategory(category);

			if (medias != null && medias.size() > 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("Media return from database: " + medias.size());
				}

				if (redisDao.isRedisUp()) {
					if (logger.isDebugEnabled()) {
						logger.debug("Multi thread to save medias to redis ...");
					}
					String key = category + catetoryKey;
					SaveMediasToRedis saveToRedis = new SaveMediasToRedis(key,
							medias);
					Thread t = new Thread(saveToRedis);
					t.start();
				}
			} else {

				if (logger.isDebugEnabled()) {
					logger.debug("No data from database: " + category);
				}

			}
		}

		return medias;

	}

	class SaveMediasToRedis implements Runnable {

		private String key;
		private List<CarMedia> medias;

		public SaveMediasToRedis(List<CarMedia> m) {
			this.medias = m;
		}

		public SaveMediasToRedis(String w, List<CarMedia> m) {
			this.key = w;
			this.medias = m;
		}

		@Override
		public void run() {

			String theadName = Thread.currentThread().getName();

			if (logger.isDebugEnabled()) {
				logger.debug(theadName + " running...");
			}

			if (key == null) {
				redisDao.save(medias);
			} else {
				Set<CarMedia> set = new HashSet<CarMedia>();
				set.addAll(medias);
				redisDao.save(key, set);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(theadName + " completed...");
			}
		}
	}
}
