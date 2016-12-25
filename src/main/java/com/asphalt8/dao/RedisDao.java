package com.asphalt8.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarFeature;
import com.asphalt8.entity.CarInfo;
import com.asphalt8.entity.CarIntroduction;
import com.asphalt8.entity.CarMedia;
import com.asphalt8.entity.CarStat;
import com.asphalt8.entity.Redis;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class RedisDao implements BaseDao<Car> {

	@Resource
	Redis redis;

	private static Logger logger = Logger.getLogger(RedisDao.class);

	private RuntimeSchema<Car> carSchema = RuntimeSchema.createFrom(Car.class);
	private RuntimeSchema<CarInfo> carInfoSchema = RuntimeSchema
			.createFrom(CarInfo.class);
	private RuntimeSchema<CarFeature> carFeatureSchema = RuntimeSchema
			.createFrom(CarFeature.class);
	private RuntimeSchema<CarIntroduction> carIntroductionSchema = RuntimeSchema
			.createFrom(CarIntroduction.class);
	private RuntimeSchema<CarMedia> carMediaSchema = RuntimeSchema
			.createFrom(CarMedia.class);
	private RuntimeSchema<CarStat> carStatSchema = RuntimeSchema
			.createFrom(CarStat.class);
	private static int timeout;
	private final String infoKey;
	private final String featureKey;
	private final String introKey;
	private final String statKey;
	private final String mediaKey;
	private final String categoryKey;
	private final String allCarKey;
	private final String pong;
	private static final String OK = "OK";
	private String topNitroSpeedKey;
	private String topNitroKey;
	private String topHandlingKey;
	private String topSpeedKey;
	private String topAccelerationKey;
	private static boolean isUp = true;

	public RedisDao(Integer timeout, String infoKey, String featureKey,
			String introKey, String statKey, String mediaKey,
			String categoryKey, String allCarKey, String topNitroSpeedKey,
			String topNitroKey, String topHandlingKey, String topSpeedKey,
			String topAccelerationKey, String pong) {

		RedisDao.timeout = timeout;

		this.infoKey = infoKey;
		this.featureKey = featureKey;
		this.introKey = introKey;
		this.statKey = statKey;
		this.mediaKey = mediaKey;
		this.categoryKey = categoryKey;
		this.allCarKey = allCarKey;
		this.topNitroSpeedKey = topNitroSpeedKey;
		this.topNitroKey = topNitroKey;
		this.topHandlingKey = topHandlingKey;
		this.topSpeedKey = topSpeedKey;
		this.topAccelerationKey = topAccelerationKey;
		this.pong = pong;
	}

	@Override
	public Car queryByCarId(int id) {
		return null;
	}

	@Override
	public Car queryByCarName(String name) {

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + name);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				Car car = queryRedisByName(name, carSchema, jedis);
				return car;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Faile to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	/**
	 * 泛型方法支持不同类型的数据查询
	 * 
	 * @param name
	 * @param schema
	 * @param jedis
	 * @return
	 */
	private static <T> T queryRedisByName(String name, RuntimeSchema<T> schema,
			Jedis jedis) {

		if (logger.isDebugEnabled()) {
			logger.debug("Key => " + name);
		}

		try {
			byte[] key = name.toLowerCase().getBytes();
			byte[] value = jedis.get(key);

			if (value != null) {
				T t = schema.newMessage();
				ProtostuffIOUtil.mergeFrom(value, t, schema);
				if (logger.isDebugEnabled()) {
					logger.debug("Return from redis: " + t);
				}
				return t;
			} else {
				logger.debug("No data from redis for key: "
						+ new String(key, "UTF-8"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	private static <T> List<T> queryListByName(String name,
			RuntimeSchema<T> schema, Jedis jedis) {

		if (logger.isDebugEnabled()) {
			logger.debug("Key => " + name);
		}

		try {
			byte[] key = name.toLowerCase().getBytes();
			List<byte[]> values = jedis.lrange(key, 0, -1);

			if (logger.isDebugEnabled()) {
				logger.debug("length of values: " + values.size());
			}

			List<T> objects = new ArrayList<T>();

			for (byte[] value : values) {
				if (value != null) {
					T t = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(value, t, schema);

					if (logger.isDebugEnabled()) {
						logger.debug("Object return from redis: " + t);
					}

					objects.add(t);
				}
			}

			return objects;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Integer save(Car car) {

		Jedis jedis = null;

		try {

			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(car.getCarName(), car, carSchema,
						jedis);

				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer save(CarInfo info) {

		String key = info.getCar().getCarName() + infoKey;

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, info, carInfoSchema, jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer save(CarFeature feature) {

		String key = feature.getCarName() + featureKey;

		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, feature, carFeatureSchema,
						jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer save(List<CarMedia> medias) {

		String key = medias.get(0).getCarName() + ":"
				+ medias.get(0).getMediaType();

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, medias, carMediaSchema, jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer saveAllCars(List<CarMedia> medias) {

		String key = allCarKey + mediaKey;
		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, medias, carMediaSchema, jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer save(CarIntroduction intro) {

		String key = intro.getCarName() + introKey;

		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, intro, carIntroductionSchema,
						jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer save(CarStat stat) {

		String key = stat.getCarName() + statKey;

		Jedis jedis = null;

		try {

			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, stat, carStatSchema, jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	private static <T> String saveToRedis(String name, T t,
			RuntimeSchema<T> schema, Jedis jedis) {

		String result = "fail";

		try {

			if (logger.isDebugEnabled()) {
				logger.debug("save object to redis: " + t);
				logger.debug("key => " + name);
			}

			byte[] key = name.toLowerCase().getBytes();

			if (!jedis.exists(key)) {

				byte[] value = ProtostuffIOUtil
						.toByteArray(t, schema, LinkedBuffer
								.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

				result = jedis.setex(key, timeout, value);

				if (logger.isDebugEnabled()) {
					logger.debug("save redis result: " + result);
				}

			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Duplicate key in redis: "
							+ new String(key, "UTF-8"));
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	private static <T> String saveToRedis(String name, Collection<T> data,
			RuntimeSchema<T> schema, Jedis jedis) {

		String result = "fail";

		try {

			if (logger.isDebugEnabled()) {
				logger.debug("key => " + name);
			}

			byte[] key = name.toLowerCase().getBytes();

			if (!jedis.exists(key)) {

				for (T t : data) {

					byte[] value = ProtostuffIOUtil
							.toByteArray(t, schema, LinkedBuffer
									.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

					Long push = jedis.rpush(key, value);

					if (logger.isDebugEnabled()) {
						logger.debug("Push redis result: " + push);
						logger.debug("Save to redis: " + t);
					}
				}

				long length = jedis.llen(key);

				if (logger.isDebugEnabled()) {
					logger.debug("check length by redis key: "
							+ new String(key, "UTF-8"));
					logger.debug("value length in redis: " + length);
				}
				result = OK;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Duplicate key in redis : "
							+ new String(key, "UTF-8"));
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	public List<Car> queryCarByKeyWord(String word) {

		List<Car> cars;
		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				cars = queryListByName(word, carSchema, jedis);
				return cars;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public CarInfo queryCarInfoByName(String name) {
		String key = name + infoKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + name);
		}

		Jedis jedis = null;

		try {

			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				CarInfo info = queryRedisByName(key, carInfoSchema, jedis);
				return info;
			} else {
				logger.error("==> Redis is down ...");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public CarFeature queryCarFeatureByName(String name) {

		String key = name + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + name);
		}

		Jedis jedis = null;

		try {

			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				CarFeature feature = queryRedisByName(key, carFeatureSchema,
						jedis);
				return feature;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public CarIntroduction queryCarIntroductionByName(String name) {

		String key = name + introKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + name);
		}

		Jedis jedis = null;

		try {

			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				CarIntroduction intro = queryRedisByName(key,
						carIntroductionSchema, jedis);

				return intro;
			} else {
				logger.error("==> Redis is down ...");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public List<CarMedia> queryCarMediaByName(String name, String type) {

		String key = name + ":" + type;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + name);
		}

		Jedis jedis = null;

		try {

			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarMedia> medias = queryListByName(key, carMediaSchema,
						jedis);
				return medias;
			} else {
				logger.error("==> Redis is down ...");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public List<CarMedia> queryAllCar() {

		String key = allCarKey + mediaKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarMedia> medias = queryListByName(key, carMediaSchema,
						jedis);
				return medias;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;

	}

	public CarStat queryCarStatByName(String name) {

		String key = name + statKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + name);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				CarStat stat = queryRedisByName(key, carStatSchema, jedis);
				return stat;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	/**
	 * 参数不能写成save(List<Car> cars), 否则会与别一个方法: <br>
	 * save(List<CarMedia> medias)产生泛型的重载冲突
	 * 
	 * @param cars
	 * @return
	 */
	public Integer save(String key, List<Car> cars) {

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, cars, carSchema, jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public Integer save(List<CarFeature> features, String key) {
		Jedis jedis = null;
		String keyword = key + featureKey;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(keyword, features,
						carFeatureSchema, jedis);

				if (logger.isDebugEnabled()) {
					logger.debug("Redis save result: " + result);
				}

				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return 0;
	}

	public Integer save(String key, Set<CarMedia> medias) {

		String keyword = key + mediaKey;

		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(keyword, medias, carMediaSchema,
						jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;
	}

	public List<CarMedia> queryMediaByKeyword(String keyword) {
		List<CarMedia> medias;

		String word = keyword + mediaKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query media from redis: " + keyword);
		}

		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				medias = queryListByName(word, carMediaSchema, jedis);
				return medias;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public List<CarFeature> queryAllCarsFeature() {
		String key = allCarKey + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarFeature> fs = queryListByName(key, carFeatureSchema,
						jedis);
				return fs;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return null;
	}

	public Integer saveAllCars(Set<CarFeature> features) {

		String key = allCarKey + featureKey;
		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				String result = saveToRedis(key, features, carFeatureSchema,
						jedis);
				if (OK.equalsIgnoreCase(result)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return 0;

	}

	public List<CarMedia> queryMediaByCatetory(String category) {
		List<CarMedia> medias;
		String word = category + categoryKey + mediaKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query media from redis by catetory: " + category);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				medias = queryListByName(word, carMediaSchema, jedis);
				return medias;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		return null;
	}

	public void flushRedis() {

		if (logger.isDebugEnabled()) {
			logger.debug("==> Flush all data in redis ... ");
		}

		Jedis jedis = null;
		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				jedis.flushAll();
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error("Fail to flush redis: " + e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Flush redis completed ... ");
		}
	}

	public boolean isRedisUp() {

		if (!isUp) {
			return false;
		}

		Jedis jedis = null;

		try {

			jedis = redis.getResource();

			if (jedis != null) {
				String ping = jedis.ping();
				if (pong.equalsIgnoreCase(ping)) {
					isUp = true;
				} else {
					logger.error("==> Redis is down ...");
				}
			}

		} catch (JedisConnectionException e) {
			isUp = false;
			logger.error("==> Redis is down ...");
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					isUp = false;
					logger.error("Fail to close redis connection ...");
				}
			}
		}

		return isUp;
	}

	public List<CarFeature> queryTopNitroSpeed() {
		String key = topNitroSpeedKey + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarFeature> fs = queryListByName(key, carFeatureSchema,
						jedis);
				return fs;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return null;
	}

	public List<CarFeature> queryTopNitro() {
		String key = topNitroKey + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarFeature> fs = queryListByName(key, carFeatureSchema,
						jedis);
				return fs;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return null;
	}

	public List<CarFeature> queryTopHandling() {

		String key = topHandlingKey + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarFeature> fs = queryListByName(key, carFeatureSchema,
						jedis);
				return fs;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return null;
	}

	public List<CarFeature> queryTopSpeed() {

		String key = topSpeedKey + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarFeature> fs = queryListByName(key, carFeatureSchema,
						jedis);
				return fs;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return null;
	}

	public List<CarFeature> queryTopAcceleration() {
		String key = topAccelerationKey + featureKey;

		if (logger.isDebugEnabled()) {
			logger.debug("Query from redis: " + key);
		}

		Jedis jedis = null;

		try {
			jedis = redis.getResource();
			String ping = jedis.ping();
			if (pong.equalsIgnoreCase(ping)) {
				List<CarFeature> fs = queryListByName(key, carFeatureSchema,
						jedis);
				return fs;
			} else {
				logger.error("==> Redis is down ...");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					logger.error("Fail to close redis connection: " + e);
				}
			}
		}
		return null;
	}
}
