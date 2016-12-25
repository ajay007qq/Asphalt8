package com.asphalt8.dao;

import java.util.List;

import com.asphalt8.entity.CarFeature;

public interface CarFeatureDao extends BaseDao<CarFeature> {

	int insertCarFeature(CarFeature feature);

	List<CarFeature> queryAllCars();

}
