package com.asphalt8.service;

import java.util.List;

import com.asphalt8.entity.CarMedia;
import com.asphalt8.exception.NoCarFoundException;

public interface CarMediaService {

	int queryNextImageNumById(int carId);

	List<CarMedia> queryById(int carId, String type);

	List<CarMedia> queryByName(String name, String type);

	int insertCarMedia(CarMedia carMedia);

	List<CarMedia> queryAllCars() throws NoCarFoundException;

	List<CarMedia> queryCarsByKeyword(String keyword);

	List<CarMedia> queryCarsByCategory(String category);

}
