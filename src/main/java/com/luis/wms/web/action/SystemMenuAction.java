package com.luis.wms.web.action;

import com.luis.wms.domain.SystemMenu;
import com.luis.wms.query.SystemMenuQueryObject;
import com.luis.wms.service.ISystemMenuService;
import com.luis.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SystemMenuAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private ISystemMenuService systemMenuService;

    @Getter
    private SystemMenu systemMenu = new SystemMenu();

    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @RequiredPermission("系统菜单列表")
    public String execute() throws Exception {
        try {
            ActionContext.getContext().put("menus", systemMenuService.getParentMenuList(systemMenuService.get(qo.getParentId())));
            //=============================================
            ActionContext.getContext().put("pageResult", systemMenuService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("系统菜单新增/更改")
    public String input() throws Exception {
        if(qo.getParentId() > 0){
            ActionContext.getContext().put("parentName", systemMenuService.get(qo.getParentId()).getName());
        }else{
            ActionContext.getContext().put("parentName", "根目录");
        }
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
        return INPUT;
    }

    public String getMenusByParentSn() throws Exception{
        List<SystemMenu> menus = systemMenuService.getMenusByParentSn(qo.getParentSn());
        List<Object> jsonList = new ArrayList<>();
        for (SystemMenu menu : menus) {
            jsonList.add(menu.toJson());
        }
        putJson(jsonList);
        return NONE;
    }

    @RequiredPermission("系统菜单保存/更新")
    public String saveOrUpdate() {
        try {
            if(qo.getParentId() > 0){
                systemMenu.setParent(systemMenuService.get(qo.getParentId()));
            }
            if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
                super.addActionMessage("保存成功");
            } else {
                systemMenuService.update(systemMenu);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("系统菜单删除")
    public String delete() throws Exception {
        if (systemMenu.getId() != null) {
            systemMenuService.delete(systemMenu);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
    }
}
