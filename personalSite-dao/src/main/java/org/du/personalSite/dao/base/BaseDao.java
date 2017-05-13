package org.du.personalSite.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by duqinyuan
 */
public interface BaseDao<T>
{
	T get(Class<T> entityClazz, Serializable id) throws Exception;

	Serializable save(T entity) throws Exception;

	void update(T entity) throws Exception;

	void delete(T entity) throws Exception;

	void delete(Class<T> entityClazz, Serializable id) throws Exception;

	List<T> findAll(Class<T> entityClazz) throws Exception;

	long findCount(Class<T> entityClazz) throws Exception;
}
