package com.asphalt8.service;

import java.util.List;

import com.asphalt8.entity.Car;

public interface CarService extends BaseService<Car> {

	List<Car> queryCarByVendor(String vendor);

	List<Car> queryAllCars();

	List<Car> queryCarByKeyWord(String keyword);

	List<String> queryVendorsByKeyWord(String keyword);

	List<Car> queryCarsForAdvertisment(String name);

	int insertCar(Car car);

	List<String> queryCarNameByKeyWord(String word);

}
