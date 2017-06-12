package com.luis.wms.dao;

import com.luis.wms.domain.Employee;
import com.luis.wms.query.EmployeeQueryObject;
import com.luis.wms.query.PageResult;

public interface IEmployeeDAO extends IGenericDAO<Employee>{

	PageResult query(EmployeeQueryObject qo);

	Employee queryForObject(String username, String password);
}
