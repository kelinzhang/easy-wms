package com.luis.wms.web.action;

import com.luis.wms.service.ISystemMenuService;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.Role;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IPermissionService;
import com.luis.wms.service.IRoleService;
import com.luis.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;

    @Getter
    private Role role = new Role();

    @Getter
    QueryObject qo = new QueryObject();

    @Override
    @RequiredPermission("角色列表")
    public String execute() throws Exception {
        PageResult result = roleService.query(qo);
        ActionContext.getContext().put("pageResult", result);
        return "list";
    }

    @RequiredPermission("角色新增和修改")
    public String input() {
        ActionContext.getContext().put("permissions", permissionService.listAll());
        ActionContext.getContext().put("menus", systemMenuService.listParentMenu());
        if (role != null && role.getId() != null) {
            role = roleService.get(role.getId());
        }
        return INPUT;
    }

    @RequiredPermission("角色添加和更改")
    public String saveOrUpdate() {
        try {
            if (role.getId() != null) {
                roleService.update(role);
                addActionMessage("更改成功");
            } else {
                roleService.save(role);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @RequiredPermission("角色删除")
    public String delete() {
        roleService.delete(role);
        addActionMessage("删除成功");
        return SUCCESS;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        role.getPermissions().clear();
        role.getMenus().clear();
    }
}
