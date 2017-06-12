package com.luis.wms.dao.impl;

import com.luis.wms.dao.ISystemMenuDAO;
import com.luis.wms.domain.Employee;
import com.luis.wms.domain.Role;
import com.luis.wms.domain.SystemMenu;
import com.luis.wms.vo.SystemMenuVO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements ISystemMenuDAO {

    @Override
    public List<SystemMenuVO> getParentMenuList(SystemMenu systemMenu) {
        List<SystemMenuVO> menus = new ArrayList<>();
        getParent(systemMenu,menus);
        Collections.reverse(menus);
        return menus;
    }

    @Override
    public List<SystemMenu> listParentMenu() {
        Session session = super.getSessionFactory().getCurrentSession();
        String hql ="select obj from SystemMenu obj where obj.parent is not null";
        return session.createQuery(hql).list();
    }

    @Override
    public List<SystemMenu> getMenusByParentSn(String parentSn) {
        Session session = super.getSessionFactory().getCurrentSession();
        String hql = "select sm from SystemMenu sm where sm.parent.sn = ?";
        return session.createQuery(hql).setParameter(0,parentSn).list();
    }

    @Override
    public List<SystemMenu> getMenusByParentSn(String parentSn, List<Role> roles) {
        String hql = "select sm from Role r join r.menus sm where sm.parent.sn = :psn and r in :rs";
        Session session = super.getSessionFactory().getCurrentSession();
        return session.createQuery(hql).setParameter("psn",parentSn).setParameterList("rs",roles).list();
    }

    private void getParent(SystemMenu systemMenu, List<SystemMenuVO> menus) {
        if(systemMenu != null){
            SystemMenuVO vo = new SystemMenuVO();
            vo.setId(systemMenu.getId());
            vo.setName(systemMenu.getName());
            menus.add(vo);
            getParent(systemMenu.getParent(),menus);
        }
    }
}
