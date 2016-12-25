package com.asphalt8.dao;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarFeatureDaoTest {

	@Resource
	private CarFeatureDao carFeatureDao;

	@Test
	public void testQueryById() {
		int id = 1;
		CarFeature f = carFeatureDao.queryByCarId(id);
		System.out.println(f);
	}

	@Test
	public void testInsertCarFeature() {
		fail("Not yet implemented");
	}

}
