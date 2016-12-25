package com.asphalt8.service;

import java.util.List;

import com.asphalt8.entity.CarFeature;

public interface TopRankService {

	List<CarFeature> queryTopNitroSpeed();

	List<CarFeature> queryTopNitro();

	List<CarFeature> queryTopHandling();

	List<CarFeature> queryTopSpeed();

	List<CarFeature> queryTopAcceleration();

}
