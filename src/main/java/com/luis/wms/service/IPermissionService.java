package com.luis.wms.service;

import java.util.List;

import com.luis.wms.domain.Permission;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

public interface IPermissionService {

	void delete(Permission entity);
	
	List<Permission> listAll();
	
	PageResult query(QueryObject qo);

	void reload();
}
