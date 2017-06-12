package com.luis.wms.service.impl;

import java.util.List;

import lombok.Setter;

import com.luis.wms.dao.IRoleDAO;
import com.luis.wms.domain.Role;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

	@Setter
	private IRoleDAO roleDAO;
	
	@Override
	public void save(Role entity) {
		roleDAO.save(entity);
	}

	@Override
	public void delete(Role entity) {
		roleDAO.delete(entity);
	}

	@Override
	public void update(Role entity) {
		roleDAO.update(entity);	
	}

	@Override
	public Role get(Long id) {
		return roleDAO.get(id);
	}

	@Override
	public List<Role> listAll() {
		return roleDAO.listAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return roleDAO.query(qo);
	}

}
