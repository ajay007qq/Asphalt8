/**
 * 
 */
package com.asphalt8.service;

import com.asphalt8.exception.NoCarFoundException;

/**
 * @author fengzhijie
 *
 */
public interface BaseService<T> {

	T queryByCarId(int id) throws NoCarFoundException;

	T queryByCarName(String name) throws NoCarFoundException;

	Integer save(T t);

}
