package com.luis.wms.dao;

import com.luis.wms.domain.Permission;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

public interface IPermissionDAO extends IGenericDAO<Permission>{

	PageResult query(QueryObject qo);

}
