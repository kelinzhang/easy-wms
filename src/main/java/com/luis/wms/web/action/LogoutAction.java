package com.luis.wms.web.action;

import com.luis.wms.util.UserContext;

public class LogoutAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		UserContext.setCurrentUser(null);
		return SUCCESS;
	}
}
