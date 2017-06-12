package com.luis.wms.web.action;

import lombok.Getter;
import lombok.Setter;

import com.luis.wms.service.IEmployeeService;

public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Getter@Setter
	private IEmployeeService employeeService;
	@Setter
	String username;
	@Setter
	String password;
	
	@Override
	public String execute() throws Exception {
		try {
			employeeService.checkLogin(username,password);
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError("账号或密码错误!");
			return LOGIN;
		}
		return SUCCESS;
	}
}
