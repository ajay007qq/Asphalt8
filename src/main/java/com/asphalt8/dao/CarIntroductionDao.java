/**
 * 
 */
package com.asphalt8.dao;

import com.asphalt8.entity.CarIntroduction;

/**
 * @author fengzhijie
 *
 */
public interface CarIntroductionDao extends BaseDao<CarIntroduction> {
	int insertCarIntroduction(CarIntroduction instroduction);
}
