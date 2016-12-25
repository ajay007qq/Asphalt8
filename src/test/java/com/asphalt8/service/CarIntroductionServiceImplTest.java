package com.asphalt8.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarIntroduction;
import com.asphalt8.exception.NoCarFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarIntroductionServiceImplTest {

	@Autowired
	CarIntroductionService carIntroductionService;

	@Test
	public void testQueryByCarId() throws NoCarFoundException {
		int id = 3;
		CarIntroduction introduction = carIntroductionService.queryByCarId(id);
		System.out.println(introduction);
	}

	@Test
	public void save() {

		CarIntroduction introduction = new CarIntroduction();
		introduction.setCarId(4);
		introduction.setDescription("sssssssssss");
		Integer i = carIntroductionService.save(introduction);

		System.out.println("junit>> " + i);

	}

	@Test
	public void testQueryByCarName() throws NoCarFoundException {
		String name = "AUDI R8 E-TRON";
		CarIntroduction intro = carIntroductionService.queryByCarName(name);
		System.out.println("junit >> " + intro);
	}

	@Test
	public void testInsertCarIntroduction() {
		fail("Not yet implemented");
	}

}
