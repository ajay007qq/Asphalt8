package com.asphalt8.service;

import com.asphalt8.entity.CarInfo;

public interface CarInfoService extends BaseService<CarInfo> {

	public CarStatService getCarStatService();

	public CarIntroductionService getCarIntroductionServices();

	public CarFeatureService getCarFeatureService();

	public CarMediaService getCarMediaService();

}
