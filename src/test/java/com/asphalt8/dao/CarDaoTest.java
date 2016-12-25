package com.asphalt8.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.Car;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarDaoTest {

	@Resource
	private CarDao carDao;

	@Test
	public void testQueryById() {
		int id = 1;
		Car car = carDao.queryByCarId(id);
		System.out.println(car);
	}

	@Test
	public void testQueryByName() {
		String name = "Dodge Dart GT";
		Car car = carDao.queryByCarName(name);
		System.out.println(car);
	}

	@Test
	public void testQueryByVendor() {
		String vendor = "Dodge";
		List<Car> cars = carDao.queryByVendor(vendor);
		System.out.println("Total: " + cars.size());
		for (Car c : cars) {
			System.out.println(c);
		}

	}

	@Test
	public void testQueryAll() {
		List<Car> cars = carDao.queryAllCars();
		System.out.println("Total: " + cars.size());
		for (Car c : cars) {
			System.out.println(c);
		}
	}

	@Test
	public void testInsertCar() {
		Car car = new Car();
		car.setCarName("Audi RS 3 Sportback");
		car.setCarCategory("C");
		car.setVendor("Audi");
		int ret = carDao.insertCar(car);
		System.out.println("insert car return: " + ret);
	}
}
