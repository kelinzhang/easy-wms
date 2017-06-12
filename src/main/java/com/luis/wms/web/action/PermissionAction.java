package com.luis.wms.web.action;

import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.Permission;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IPermissionService;
import com.luis.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

public class PermissionAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Getter@Setter
	private IPermissionService permissionService;
	
	@Getter
	private QueryObject qo = new QueryObject();
	
	@Getter
	private Permission permission = new Permission(); 
	
	@Override
	@RequiredPermission("权限列表")
	public String execute() throws Exception {
		ActionContext.getContext().put("pageResult", permissionService.query(qo));
		return "list";
	}
	
	@RequiredPermission("权限删除")
	public String delete(){
		permissionService.delete(permission);
		return SUCCESS;
	}
	
	@RequiredPermission("权限加载")
	public String reload(){
		permissionService.reload();
		return SUCCESS;
	}

	@RequiredPermission("测试")
	public void pop(){}
}
