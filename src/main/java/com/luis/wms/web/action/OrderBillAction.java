package com.luis.wms.web.action;

import com.luis.wms.query.QueryObject;
import com.luis.wms.query.OrderBillQueryObject;
import com.luis.wms.service.ISupplierService;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.OrderBill;
import com.luis.wms.service.IOrderBillService;
import com.luis.wms.util.RequiredPermission;

public class OrderBillAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IOrderBillService orderBillService;
    @Setter
    private ISupplierService supplierService;

    @Getter
    private OrderBill orderBill = new OrderBill();

    @Getter
    private QueryObject qo = new OrderBillQueryObject();

    @RequiredPermission("采购订单列表")
    public String execute() throws Exception {
        try {
            putContext("suppliers",supplierService.listAll());
            putContext("pageResult", orderBillService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("采购订单新增/更改")
    public String input() throws Exception {
        putContext("suppliers",supplierService.listAll());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("采购订单查看")
    public String view() throws Exception {
        putContext("suppliers",supplierService.listAll());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return "view";
    }

    @RequiredPermission("采购订单审核")
    public String audit(){
        orderBillService.audit(orderBill.getId());
        return NONE;
    }

    @RequiredPermission("采购订单保存/更新")
    public String saveOrUpdate() {
        try {
            if (orderBill.getId() == null) {
                orderBillService.save(orderBill);
                super.addActionMessage("保存成功");
            } else {
                orderBillService.update(orderBill);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("采购订单删除")
    public String delete() throws Exception {
        if (orderBill.getId() != null) {
            orderBillService.delete(orderBill);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBill.setSupplier(null);
        }
        orderBill.getItems().clear();
    }
}
