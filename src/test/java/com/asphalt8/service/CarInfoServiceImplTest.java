package com.asphalt8.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarInfo;
import com.asphalt8.exception.NoCarFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarInfoServiceImplTest {

	@Autowired
	@Qualifier("carInfoServiceImpl")
	private CarInfoService carInfoService;

	@Test
	public void testQueryByCarId() throws NoCarFoundException {
		int id = 7;
		CarInfo info;
		info = carInfoService.queryByCarId(id);
		System.out.println("junit >> testQueryByCarId");
		System.out.println(info);
	}

	@Test
	public void testQueryByCarName() throws NoCarFoundException {
		// String name = "Renault DeZir";
		// Mini Cooper S Roadster
		String name = "Mini Cooper S Roadster";
		CarInfo carInfo;
		carInfo = carInfoService.queryByCarName(name);
		System.out.println("junit >> testQueryByCarName");
		System.out.println(carInfo);
	}

}
