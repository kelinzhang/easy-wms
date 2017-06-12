package com.luis.wms.dao;

import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IGenericDAO<T> {

	void save(T entity);
	
	void delete(T entity);
	
	void update(T entity);
	
	T get(Long id);
	
	List<T> listAll();

    void batchDelete(List<Long> ids);

	PageResult query(QueryObject qo);
}
