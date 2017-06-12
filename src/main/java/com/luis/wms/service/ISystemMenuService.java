package com.luis.wms.service;

import com.luis.wms.domain.SystemMenu;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuService {
	void save(SystemMenu entity);

	void update(SystemMenu entity);

	void delete(SystemMenu entity);

	SystemMenu get(Long id);

	List<SystemMenu> listAll();

	PageResult query(QueryObject qo);

    List<SystemMenuVO> getParentMenuList(SystemMenu systemMenu);

    List<SystemMenu> listParentMenu();

    List<SystemMenu> getMenusByParentSn(String parentSn);
}
