package com.luis.wms.service;

import com.luis.wms.domain.Department;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {

	
	void save(Department entity);
	
	void delete(Department entity);
	
	void update(Department entity);
	
	Department get(Long id);
	
	List<Department> listAll();

	PageResult query(QueryObject qo);
}
