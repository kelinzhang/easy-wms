package com.luis.wms.service;

import com.luis.wms.domain.Employee;
import com.luis.wms.query.EmployeeQueryObject;
import com.luis.wms.query.PageResult;

import java.util.List;

public interface IEmployeeService {

	
	void save(Employee entity);
	
	void delete(Employee entity);
	
	void update(Employee entity);
	
	Employee get(Long id);
	
	List<Employee> listAll();
	
	PageResult query(EmployeeQueryObject qo);

	Employee checkLogin(String username, String password);

	void batchDelete(List<Long> ids);
}
