package com.asphalt8.comparator;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarFeature;
import com.asphalt8.exception.NoCarFoundException;
import com.asphalt8.service.CarFeatureService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class ComporatorFactoryTest {

	@Autowired
	ComparatorFactory cmpFactory;

	@Autowired
	CarFeatureService featureService;

	@Test
	public void testSpeed() throws NoCarFoundException {

		SpeedComparator spComparator = cmpFactory.getSpeedComparator();

		List<CarFeature> fs = featureService.queryAllCar();

		Collections.sort(fs, spComparator);

		List<CarFeature> result = fs.subList(0, 10);

		System.out.println("Top 10 car - Speed");

		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + ". " + result.get(i).getCarName() + ": "
					+ result.get(i).getMaxSpeed());
		}

	}

	@Test
	public void testAcceleration() {

		AccelerationComparator aclComporator = cmpFactory
				.getAccelerationComparator();

		List<CarFeature> fs = featureService.queryAllCar();

		Collections.sort(fs, aclComporator);

		List<CarFeature> result = fs.subList(0, 10);

		System.out.println("Top 10 car - Acceleration");

		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + ". " + result.get(i).getCarName() + ": "
					+ result.get(i).getMaxAcceleration());
		}

	}

	@Test
	public void testHandling() {

		HandlingComparator hdlComparator = cmpFactory.getHandlingComparator();

		List<CarFeature> fs = featureService.queryAllCar();

		Collections.sort(fs, hdlComparator);

		System.out.println("Top 10 - Handling");

		List<CarFeature> result = fs.subList(0, 10);

		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + ". " + result.get(i).getCarName() + ": "
					+ result.get(i).getMaxHandling());
		}

	}

	@Test
	public void testNitro() {

		NitroComparator ntoComparator = cmpFactory.getNitroComparator();

		List<CarFeature> fs = featureService.queryAllCar();

		Collections.sort(fs, ntoComparator);

		System.out.println("Top 10 - Nitro");

		List<CarFeature> result = fs.subList(0, 10);

		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + ". " + result.get(i).getCarName() + ": "
					+ result.get(i).getMaxNitro());
		}

	}

	@Test
	public void testNitroSpeed() {

		NitroSpeedComparator nsComparator = cmpFactory
				.getNitroSpeedComparator();

		List<CarFeature> fs = featureService.queryAllCar();

		Collections.sort(fs, nsComparator);

		System.out.println("Top 10 - Nitro Speed");

		List<CarFeature> result = fs.subList(0, 10);

		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + ". " + result.get(i).getCarName() + ": "
					+ result.get(i).getMaxNitroSpeed());
		}
	}

}
