package com.luis.wms.dao;

import com.luis.wms.domain.Role;
import com.luis.wms.domain.SystemMenu;
import com.luis.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu>{
    List<SystemMenuVO> getParentMenuList(SystemMenu systemMenu);

    List<SystemMenu> listParentMenu();

    List<SystemMenu> getMenusByParentSn(String parentSn);

    List<SystemMenu> getMenusByParentSn(String parentSn, List<Role> roles);
}
