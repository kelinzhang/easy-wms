package com.luis.wms.service;

import java.util.List;

import com.luis.wms.domain.Role;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

public interface IRoleService {

	
	void save(Role entity);
	
	void delete(Role entity);
	
	void update(Role entity);
	
	Role get(Long id);
	
	List<Role> listAll();

	PageResult query(QueryObject qo);
}
