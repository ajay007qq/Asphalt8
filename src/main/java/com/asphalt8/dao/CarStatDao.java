package com.asphalt8.dao;

import com.asphalt8.entity.CarStat;

public interface CarStatDao extends BaseDao<CarStat> {
	int insertCarStat(CarStat CarStat);
}
