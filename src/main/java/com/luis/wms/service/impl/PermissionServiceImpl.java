package com.luis.wms.service.impl;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.luis.wms.dao.IPermissionDAO;
import com.luis.wms.domain.Permission;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IPermissionService;
import com.luis.wms.util.PermissionUtil;
import com.luis.wms.util.RequiredPermission;
import com.luis.wms.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {

	@Setter
	private IPermissionDAO permissionDAO;
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	@Override
	public void reload() {
		//查询出数据库中所有的权限表达式
		List<Permission> permissions = permissionDAO.listAll();
		Set<String> permissionExp = new HashSet<>();
		for (Permission permission : permissions) {
			permissionExp.add(permission.getExpression());
		}

		//获取BaseAction所有子类中的所有方法
		Map<String, BaseAction> actionMap = ctx.getBeansOfType(BaseAction.class);
		//判断每一个action方法是否使用了requiredPermission标签
		for (BaseAction action : actionMap.values()) {
			//得到每个action类中的方法
			Method[] methods = action.getClass().getDeclaredMethods();
			//判断方法上是否存在requiredPermission标签
			for (Method method : methods) {
				String exp = PermissionUtil.buildExpression(method);
				//判断当前权限表达式是否在数据库中,如果不存在,就添加进去
				if (!permissionExp.contains(exp)) {
					if (method.isAnnotationPresent(RequiredPermission.class)) {
						RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
						Permission p = new Permission();
						p.setName(rp.value());
						p.setExpression(exp);
						permissionDAO.save(p);
					}
				}
			}
		}
	}

	@Override
	public void delete(Permission entity) {
		permissionDAO.delete(entity);
	}

	@Override
	public List<Permission> listAll() {
		return permissionDAO.listAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return permissionDAO.query(qo);
	}
}
