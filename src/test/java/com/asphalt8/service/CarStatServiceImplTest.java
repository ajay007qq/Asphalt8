package com.asphalt8.service;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarStat;
import com.asphalt8.exception.NoCarFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarStatServiceImplTest {

	private static Logger logger = Logger
			.getLogger(CarStatServiceImplTest.class);

	@Autowired
	@Qualifier("carStatServiceImpl")
	private CarStatService carStatService;

	@Test
	public void testQueryByCarId() throws NoCarFoundException {
		int id = 3;
		CarStat stat = carStatService.queryByCarId(id);
		System.out.println(stat);
		logger.debug(stat);
	}

	@Test
	public void testQueryByCarName() throws NoCarFoundException {
		String name = "Renault DeZir";
		CarStat stat = carStatService.queryByCarName(name);
		System.out.println(stat);
	}

	@Test
	public void testInsertCarStat() {
		fail("Not yet implemented");
	}

}
