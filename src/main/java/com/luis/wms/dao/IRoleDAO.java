package com.luis.wms.dao;

import com.luis.wms.domain.Role;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

public interface IRoleDAO extends IGenericDAO<Role>{

	PageResult query(QueryObject qo);
}
