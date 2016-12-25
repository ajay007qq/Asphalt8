package com.asphalt8.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarFeature;
import com.asphalt8.exception.NoCarFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarFeatureServiceImplTest {

	@Autowired
	private CarFeatureService carFeatureService;

	@Test
	public void testQueryById() throws NoCarFoundException {
		int id = 2;
		CarFeature f = carFeatureService.queryByCarId(id);
		if (f == null) {
			fail("reutrn null object");
		}
		System.out.println("Junit >> " + f);
	}

	@Test
	public void queryByName() throws NoCarFoundException {
		String name = "Dodge Dart GT";
		CarFeature f = carFeatureService.queryByCarName(name);
		System.out.println("Junit >> " + f);
	}

	@Test
	public void testInsertCarFeature() {
		int carId = 2;
		int baseUpgradeRank = 800;
		int maxUpgradeRank = 1005;
		int baseProRank = 0;
		int maxProRank = 1138;
		double baseAcceleration = 6.2;
		double maxAcceleration = 3.26;
		double baseSpeed = 221.2;
		double maxSpeed = 273.6;
		double baseHandling = 1.105;
		double maxHandling = 1.307;
		double baseNitro = 18.5;
		double maxNitro = 23.6;

		CarFeature f = new CarFeature(carId, baseUpgradeRank, maxUpgradeRank,
				baseProRank, maxProRank, baseAcceleration, maxAcceleration,
				baseSpeed, maxSpeed, baseHandling, maxHandling, baseNitro,
				maxNitro);
		int ret = carFeatureService.insertCarFeature(f);
		System.out.println("Junit >> insert car feature return: " + ret
				+ " row");
		// will throw exception because car id = 2 already exists

	}

	@Test
	public void save() {
		int carId = 4;
		int baseUpgradeRank = 551;
		int maxUpgradeRank = 877;
		int baseProRank = 0;
		int maxProRank = 61;
		double baseAcceleration = 4.40;
		double maxAcceleration = 4.28;
		double baseSpeed = 210.1;
		double maxSpeed = 212.8;
		double baseHandling = 1.120;
		double maxHandling = 1.125;
		double baseNitro = 24.3;
		double maxNitro = 25.5;
		CarFeature f = new CarFeature(carId, baseUpgradeRank, maxUpgradeRank,
				baseProRank, maxProRank, baseAcceleration, maxAcceleration,
				baseSpeed, maxSpeed, baseHandling, maxHandling, baseNitro,
				maxNitro);
		int ret = carFeatureService.save(f);
		System.out.println("Junit >> insert car feature return: " + ret);
	}
}
