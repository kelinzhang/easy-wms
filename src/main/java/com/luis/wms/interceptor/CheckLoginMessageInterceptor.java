package com.luis.wms.interceptor;

import com.luis.wms.domain.Employee;
import com.luis.wms.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginMessageInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee current = UserContext.getCurrentUser();
		if (current == null) {
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
