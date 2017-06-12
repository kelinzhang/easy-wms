package com.luis.wms.web.action;

import com.luis.wms.domain.Department;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IDepartmentService;
import com.luis.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

public class DepartmentAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private IDepartmentService departmentService;

    @Getter
    private Department department = new Department();

    @Getter
    private QueryObject qo = new QueryObject();

    @Override
    @RequiredPermission("部门列表")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult", departmentService.query(qo));
        return "list";
    }

    @RequiredPermission("部门新增和修改")
    public String input() {
        if (department != null && department.getId() != null) {
            department = departmentService.get(department.getId());
        }
        System.out.println(department + "123");
        return INPUT;
    }

    @RequiredPermission("部门添加和更改")
    public String saveOrUpdate() {
        if (department.getId() != null) {
            departmentService.update(department);
            addActionMessage("更改成功");
        } else {
            departmentService.save(department);
            addActionMessage("添加成功");
        }
        return SUCCESS;
    }

    @RequiredPermission("部门删除")
    public String delete() {
        departmentService.delete(department);
        addActionMessage("删除成功");
        return SUCCESS;
    }
}
