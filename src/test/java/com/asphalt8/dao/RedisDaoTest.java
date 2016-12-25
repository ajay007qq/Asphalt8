package com.asphalt8.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class RedisDaoTest {

	@Autowired
	RedisDao redisDao;

	@Autowired
	CarDao carDao;

	@Test
	public void testQueryByCarName() {

		String name = "Dodge Dart GT";

		Car car = redisDao.queryByCarName(name);
		if (car == null) {
			System.out.println("No car for " + name + " in Redis!");
			System.out.println("Query car from database");
			car = carDao.queryByCarName(name);

			if (car != null) {
				System.out.println("Save car to redis: " + car);

				int ret = redisDao.save(car);

				if (ret == 1) {
					System.out.println("Redis save car successfully: " + ret);
				} else {
					fail("failed to save car to redis!");
				}
			}

		} else {
			System.out.println("Redis has return car: " + car);
		}

	}

	@Test
	public void testSave() {
		String name = "Renault DeZir";
		Car car = carDao.queryByCarName(name);
		int result = redisDao.save(car);
		if (result == 1) {
			System.out.println("Redis saved car: " + car);
		}

		Car c = redisDao.queryByCarName(name);
		if (c != null && car.equals(c)) {
			System.out.println("Return the same car from redis: " + car);
		} else {
			fail("Failed to query the same car from redis!");
		}
	}

	@Test
	public void saveFeature() {
		String name = "feat";
		CarFeature f = new CarFeature();
		f.setCarName(name);
		redisDao.save(f);
	}

	@Test
	public void queryByKeyword() {
		String w = "Au";
		redisDao.queryCarByKeyWord(w);
	}

	@Test
	public void isRedisUp() {
		if (redisDao.isRedisUp()) {
			System.out.println("Redis is running...");
		} else {
			System.out.println("Redis is down ...");
		}
	}

	@Test
	public void topNitroSpeed() {

		List<CarFeature> fs = redisDao.queryTopNitroSpeed();

		System.out.println("top nitro speed: " + fs.size());

	}

}
