package com.asphalt8.service;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class TopRankServiceImplTest {

	@Resource
	private TopRankService topRankService;

	@Test
	public void testQueryTopNitroSpeed() throws InterruptedException {
		List<CarFeature> top = topRankService.queryTopNitroSpeed();
		if (top == null || top.size() == 0) {
			fail("No data return");
		}

		Thread.sleep(2000);

		System.out.println("junit>>");

		for (int i = 0; i < top.size(); i++) {
			CarFeature f = top.get(i);
			System.out.println(i + ". " + f.getCarName() + ": "
					+ f.getMaxNitroSpeed());
		}
	}

	@Test
	public void testQueryTopNitro() throws InterruptedException {
		List<CarFeature> top = topRankService.queryTopNitro();
		if (top == null || top.size() == 0) {
			fail("No data return");
		}
		Thread.sleep(2000);

		System.out.println("junit>>");

		for (int i = 0; i < top.size(); i++) {
			CarFeature f = top.get(i);
			System.out.println(i + ". " + f.getCarName() + ": "
					+ f.getMaxNitro());
		}
	}

	@Test
	public void testQueryTopHandling() throws InterruptedException {
		List<CarFeature> top = topRankService.queryTopHandling();
		if (top == null || top.size() == 0) {
			fail("No data return");
		}
		Thread.sleep(2000);

		System.out.println("junit>>");

		for (int i = 0; i < top.size(); i++) {
			CarFeature f = top.get(i);
			System.out.println(i + ". " + f.getCarName() + ": "
					+ f.getMaxHandling());
		}
	}

	@Test
	public void testQueryTopSpeed() throws InterruptedException {
		List<CarFeature> top = topRankService.queryTopSpeed();
		if (top == null || top.size() == 0) {
			fail("No data return");
		}

		Thread.sleep(2000);

		System.out.println("junit>>");

		for (int i = 0; i < top.size(); i++) {
			CarFeature f = top.get(i);
			System.out.println(i + ". " + f.getCarName() + ": "
					+ f.getMaxSpeed());
		}
	}

	@Test
	public void testQueryTopAcceleration() throws InterruptedException {
		List<CarFeature> top = topRankService.queryTopAcceleration();
		if (top == null || top.size() == 0) {
			fail("No data return");
		}

		Thread.sleep(2000);

		System.out.println("junit>>");

		for (int i = 0; i < top.size(); i++) {
			CarFeature f = top.get(i);
			System.out.println(i + ". " + f.getCarName() + ": "
					+ f.getMaxAcceleration());
		}
	}
}
