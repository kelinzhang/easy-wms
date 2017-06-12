package com.luis.wms.service.impl;

import com.luis.wms.dao.IEmployeeDAO;
import com.luis.wms.domain.Employee;
import com.luis.wms.domain.Permission;
import com.luis.wms.domain.Role;
import com.luis.wms.query.EmployeeQueryObject;
import com.luis.wms.query.PageResult;
import com.luis.wms.service.IEmployeeService;
import com.luis.wms.util.MD5;
import com.luis.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {

	@Setter
	private IEmployeeDAO employeeDAO;
	
	@Override
	public void save(Employee entity) {
		entity.setPassword(MD5.encode(entity.getPassword()));
		employeeDAO.save(entity);
	}

	@Override
	public void delete(Employee entity) {
		employeeDAO.delete(entity);
	}

	@Override
	public void update(Employee entity) {
		employeeDAO.update(entity);	
	}

	@Override
	public Employee get(Long id) {
		return employeeDAO.get(id);
	}

	@Override
	public List<Employee> listAll() {
		return employeeDAO.listAll();
	}

	@Override
	public PageResult query(EmployeeQueryObject qo) {
		return employeeDAO.query(qo);
	}

	@Override
	public Employee checkLogin(String username, String password) {
		Employee current = employeeDAO.queryForObject(username,MD5.encode(password));
		if (current == null) {
			throw new RuntimeException("账号或密码错误!");
		}
		UserContext.setCurrentUser(current);
		//查询出当前用户的所有权限表达式,并放到session中
		Set<String> permissionSet = new HashSet<>();
		List<Role> roleList = current.getRoles();
		for (Role role : roleList) {
			List<Permission> ps = role.getPermissions();
			for (Permission permission : ps) {
				permissionSet.add(permission.getExpression());
			}
		}
		UserContext.setPermExpression(permissionSet);
		return current;
	}

	@Override
	public void batchDelete(List<Long> ids) {
		employeeDAO.batchDelete(ids);
	}

}
