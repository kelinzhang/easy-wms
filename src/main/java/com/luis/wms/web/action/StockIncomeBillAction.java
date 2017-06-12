package com.luis.wms.web.action;

import com.luis.wms.domain.StockIncomeBill;
import com.luis.wms.query.QueryObject;
import com.luis.wms.query.StockIncomeBillQueryObject;
import com.luis.wms.service.IDepotService;
import com.luis.wms.service.IStockIncomeBillService;
import com.luis.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class StockIncomeBillAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IStockIncomeBillService stockIncomeBillService;
    @Setter
    private IDepotService depotService;

    @Getter
    private StockIncomeBill stockIncomeBill = new StockIncomeBill();

    @Getter
    private QueryObject qo = new StockIncomeBillQueryObject();

    @RequiredPermission("入库订单列表")
    public String execute() throws Exception {
        try {
            putContext("depots", depotService.listAll());
            putContext("pageResult", stockIncomeBillService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("入库订单新增/更改")
    public String input() throws Exception {
        putContext("depots", depotService.listAll());
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("入库订单查看")
    public String view() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return "view";
    }

    @RequiredPermission("采购订单审核")
    public String audit() {
        Map<String, Object> jsonMap = new HashMap<>();
        try {
            stockIncomeBillService.audit(stockIncomeBill.getId());
            jsonMap.put("seccess", true);
            jsonMap.put("msg", "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("seccess", false);
            jsonMap.put("msg", e.getMessage());
        }
        putJson(jsonMap);
        return NONE;
    }

    @RequiredPermission("入库订单保存/更新")
    public String saveOrUpdate() {
        try {
            if (stockIncomeBill.getId() == null) {
                stockIncomeBillService.save(stockIncomeBill);
                super.addActionMessage("保存成功");
            } else {
                stockIncomeBillService.update(stockIncomeBill);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("入库订单删除")
    public String delete() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBillService.delete(stockIncomeBill);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
            stockIncomeBill.setDepot(null);
        }
        stockIncomeBill.getItems().clear();
    }
}
