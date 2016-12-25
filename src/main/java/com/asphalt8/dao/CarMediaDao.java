package com.asphalt8.dao;

import java.util.List;

import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarMedia;

public interface CarMediaDao extends BaseDao<Car> {

	int queryNextImageNumById(int carId);

	List<CarMedia> queryById(int carId, String type);

	int insertCarMedia(CarMedia carMedia);

	List<CarMedia> queryByName(String name, String type);

	List<CarMedia> queryAllCars();

	List<CarMedia> queryCarsByKeyword(String keyword);

	List<CarMedia> queryCarsByCategory(String category);

}
