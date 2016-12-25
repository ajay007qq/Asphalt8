package com.asphalt8.dao;

import java.util.List;

import com.asphalt8.entity.Car;

public interface CarDao extends BaseDao<Car> {

	List<Car> queryByVendor(String vendor);

	List<Car> queryAllCars();

	List<Car> queryCarsForAdvertisment(String name);

	int insertCar(Car car);

	List<Car> queryCarByKeyWord(String keyword);

	List<String> queryVendorsByKeyWord(String keyword);

	List<String> queryCarNameByKeyWord(String word);

}
