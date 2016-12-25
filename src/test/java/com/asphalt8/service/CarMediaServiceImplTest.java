package com.asphalt8.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asphalt8.entity.CarMedia;
import com.asphalt8.exception.NoCarFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring.xml" })
public class CarMediaServiceImplTest {

	@Autowired
	private CarMediaService carMediaService;

	@Test
	public void testQueryNextImageNumById() {
		int carId = 1;
		int nextImageId = carMediaService.queryNextImageNumById(carId);
		System.out.println("next image id : " + nextImageId);
	}

	@Test
	public void queryByName() {
		String name = "Audi R8 E-Tron";
		List<CarMedia> images = carMediaService.queryByName(name, "image");
		System.out.println("junit >>");
		System.out.println(images);

		CarMedia video = carMediaService.queryByName(name, "video").get(0);
		System.out.println(video);

	}

	@Test
	public void queryAllCars() throws NoCarFoundException {
		List<CarMedia> images = carMediaService.queryAllCars();
		System.out.println("queryAllCars >> total: " + images.size());
		for (CarMedia m : images) {
			System.out.println(m);
		}
	}

	@Test
	public void queryCarsByKeyword() {
		String keyword = "hon";
		List<CarMedia> images = carMediaService.queryCarsByKeyword(keyword);
		System.out.println("queryCarsByKeyword >> total: " + images.size());
		for (CarMedia m : images) {
			System.out.println(m);
		}
	}

	@Test
	public void queryCarsByCategory() {
		String cat = "c";
		List<CarMedia> images = carMediaService.queryCarsByCategory(cat);
		System.out.println("queryCarsByCategory >> total: " + images.size());
		for (CarMedia m : images) {
			System.out.println(m);
		}
	}

	@Test
	public void testInsertCarMedia() {
		int carId = 7;
		String carName = "Ford";
		String fileName = "abcd.jpg";
		String mediaType = "image";
		CarMedia image = new CarMedia(carId, carName, fileName, mediaType);
		int ret = 0;
		ret = carMediaService.insertCarMedia(image);
		if (ret == 1) {
			System.out.println("insert into table successfully : " + image);
		}
	}
}
