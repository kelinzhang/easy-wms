package com.luis.wms.interceptor;

import com.luis.wms.domain.Employee;
import com.luis.wms.util.PermissionUtil;
import com.luis.wms.util.RequiredPermission;
import com.luis.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

@SuppressWarnings("all")
public class SecurityInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee current = UserContext.getCurrentUser();
		//判断当前用户是否为超级管理员,如果是直接放行
		if (current != null && current.isAdmin()) {
			return invocation.invoke();
		} 
		//获取当前用户所请求的方法上的权限表达式,如果没有,直接放行
		Class actionClass = invocation.getProxy().getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method m = actionClass.getMethod(methodName);
		if (!m.isAnnotationPresent(RequiredPermission.class)) {
			return invocation.invoke();
		}
		//当前用户不是超级管理员且方法有权限表达式,判断当前用户的权限表达式是否包含所需要的
		Set<String> permissionSet = UserContext.getPermExpression();
		String exp = PermissionUtil.buildExpression(m);
		if (permissionSet.contains(exp)) {
			return invocation.invoke();
		}
		return "nopermission";
	}

}
