package com.luis.wms.web.action;

import com.luis.wms.query.QueryObject;
import com.luis.wms.query.SupplierQueryObject;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.Supplier;
import com.luis.wms.service.ISupplierService;
import com.luis.wms.util.RequiredPermission;

public class SupplierAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private ISupplierService supplierService;

    @Getter
    private Supplier supplier = new Supplier();

    @Getter
    private QueryObject qo = new SupplierQueryObject();

    @RequiredPermission("供应商列表")
    public String execute() throws Exception {
        try {
            ActionContext.getContext().put("pageResult", supplierService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("供应商新增/更改")
    public String input() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
        return INPUT;
    }

    @RequiredPermission("供应商保存/更新")
    public String saveOrUpdate() {
        try {
            if (supplier.getId() == null) {
                supplierService.save(supplier);
                super.addActionMessage("保存成功");
            } else {
                supplierService.update(supplier);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("供应商删除")
    public String delete() throws Exception {
        if (supplier.getId() != null) {
            supplierService.delete(supplier);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
    }
}
