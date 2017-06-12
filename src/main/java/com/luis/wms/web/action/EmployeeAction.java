package com.luis.wms.web.action;

import com.luis.wms.domain.Employee;
import com.luis.wms.query.EmployeeQueryObject;
import com.luis.wms.query.PageResult;
import com.luis.wms.service.IDepartmentService;
import com.luis.wms.service.IEmployeeService;
import com.luis.wms.service.IRoleService;
import com.luis.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private IEmployeeService employeeService;
    @Getter
    @Setter
    private IDepartmentService departmentService;
    @Getter
    @Setter
    private IRoleService roleService;

    @Getter
    private Employee employee = new Employee();

    @Getter
    EmployeeQueryObject qo = new EmployeeQueryObject();

    @Setter
    private List<Long> ids = new ArrayList<>();

    @RequiredPermission("员工批量删除")
    public String batchDelete() {
        employeeService.batchDelete(ids);
        return NONE;
    }

    @Override
    @RequiredPermission("员工列表")
    @InputConfig(methodName = "input")
    public String execute() {
        try {
            ActionContext.getContext().put("depts", departmentService.listAll());
            PageResult result = employeeService.query(qo);
            ActionContext.getContext().put("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            //把异常信息共享到action中
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("员工新增和修改")
    public String input() {
        ActionContext.getContext().put("depts", departmentService.listAll());
        ActionContext.getContext().put("roles", roleService.listAll());
        if (employee != null && employee.getId() != null) {
            employee = employeeService.get(employee.getId());
        }
        return INPUT;
    }

    @RequiredPermission("员工添加和更改")
    @InputConfig(methodName = "input")
    public String saveOrUpdate() {
        try {
            if (employee.getId() != null) {
                employeeService.update(employee);
                addActionMessage("更新成功");
            } else {
                employeeService.save(employee);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //把错误信息共享到页面
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("员工删除")
    public String delete() {
        employeeService.delete(employee);
        addActionMessage("删除成功");
        System.out.println("被删除了");
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
            employee.setDept(null);
        }
        employee.getRoles().clear();
    }
}
