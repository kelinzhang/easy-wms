package com.luis.wms.service.impl;

import com.luis.wms.dao.ISystemMenuDAO;
import com.luis.wms.domain.Employee;
import com.luis.wms.domain.SystemMenu;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.ISystemMenuService;
import com.luis.wms.util.UserContext;
import com.luis.wms.vo.SystemMenuVO;
import lombok.Setter;

import java.util.List;

public class SystemMenuServiceImpl implements ISystemMenuService {

    @Setter
    private ISystemMenuDAO systemMenuDAO;

    public void save(SystemMenu entity) {
        systemMenuDAO.save(entity);
    }

    public void update(SystemMenu entity) {
        systemMenuDAO.update(entity);
    }

    public void delete(SystemMenu entity) {
        systemMenuDAO.delete(entity);
    }

    public SystemMenu get(Long id) {
        return systemMenuDAO.get(id);
    }

    public List<SystemMenu> listAll() {
        return systemMenuDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return systemMenuDAO.query(qo);
    }

    @Override
    public List<SystemMenuVO> getParentMenuList(SystemMenu systemMenu) {
        return systemMenuDAO.getParentMenuList(systemMenu);
    }

    @Override
    public List<SystemMenu> listParentMenu() {
        return systemMenuDAO.listParentMenu();
    }

    @Override
    public List<SystemMenu> getMenusByParentSn(String parentSn) {
        Employee current = UserContext.getCurrentUser();
        if (current.isAdmin()) {
            return systemMenuDAO.getMenusByParentSn(parentSn);
        } else {
            return systemMenuDAO.getMenusByParentSn(parentSn,current.getRoles());
        }
    }
}
