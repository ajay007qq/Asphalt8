package com.asphalt8.service;

import java.util.List;

import com.asphalt8.entity.CarFeature;

public interface CarFeatureService extends BaseService<CarFeature> {

	int insertCarFeature(CarFeature feature);

	List<CarFeature> queryAllCar();

}
