/**
 * 
 */
package com.asphalt8.dao;

/**
 * @author fengzhijie
 * @see Dao泛型接口
 */
public interface BaseDao<T> {
	T queryByCarId(int id);

	T queryByCarName(String name);

	Integer save(T t);
}
