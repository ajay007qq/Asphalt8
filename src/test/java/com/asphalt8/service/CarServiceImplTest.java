package com.asphalt8.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.Car;
import com.asphalt8.exception.NoCarFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarServiceImplTest {

	@Autowired
	@Qualifier("carServiceImpl")
	private CarService carService;

	@Test
	public void testQueryCarById() throws NoCarFoundException {
		int id = 2;
		Car car = carService.queryByCarId(id);
		System.out.println(car);
	}

	@Test
	public void testQueryCarByKeyword() {
		String keyword = "ma";
		List<Car> cars = carService.queryCarByKeyWord(keyword);
		System.out.println("junit >> testQueryCarByKeyword");
		for (Car c : cars) {
			System.out.println(c);
		}
	}

	@Test
	public void testQueryCarsForAdvertisment() {
		String name = "Renault DeZir";
		List<Car> cars = carService.queryCarsForAdvertisment(name);
		System.out.println("junit>>");
		for (Car c : cars) {
			System.out.println(c);
		}
	}

	@Test
	public void queryVendorsByKeyWord() {
		String str = "do";
		List<String> vendors = carService.queryVendorsByKeyWord(str);
		System.out.println("junit >>");
		System.out.println(vendors);
	}

	@Test
	public void queryCarNameByKeyWord() {
		String str = "ford";
		List<String> name = carService.queryCarNameByKeyWord(str);
		System.out.println("junit >>");
		System.out.println(name);

	}

	@Test
	public void saveCar() {

		Car car = new Car();
		car.setCarName("Audi R8 e-tron");
		car.setCarCategory("D");
		car.setPrice(13000); // update price
		car.setVendor("Audi");

		Car c;
		try {
			c = carService.queryByCarName(car.getCarName());

			System.out.println("Junit>>saveCar");
			System.out.println(c); // print old car

			carService.save(car); // save new car info
			Car d = carService.queryByCarName(car.getCarName());
			System.out.println(d);
		} catch (NoCarFoundException e) {
			e.printStackTrace();
			fail("junit>> failed to query car " + car.getCarName());
		}
	}

	@Test
	public void testQueryCarbyName() throws NoCarFoundException {
		String name = "Audi RS 3 Sportback";
		Car car = carService.queryByCarName(name);
		System.out.println("junit >>");
		System.out.println(car);
	}

}
