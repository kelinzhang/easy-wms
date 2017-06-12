package com.luis.wms.web.action;

import com.luis.wms.domain.StockOutcomeBill;
import com.luis.wms.query.QueryObject;
import com.luis.wms.query.StockOutcomeBillQueryObject;
import com.luis.wms.service.IClientService;
import com.luis.wms.service.IDepotService;
import com.luis.wms.service.IStockOutcomeBillService;
import com.luis.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class StockOutcomeBillAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IStockOutcomeBillService stockOutcomeBillService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IClientService clientService;

    @Getter
    private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();

    @Getter
    private QueryObject qo = new StockOutcomeBillQueryObject();

    @RequiredPermission("出库订单列表")
    public String execute() throws Exception {
        try {
            putContext("depots", depotService.listAll());
            putContext("clients", clientService.listAll());
            putContext("pageResult", stockOutcomeBillService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("出库订单查看")
    public String view() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return "view";
    }

    @RequiredPermission("出库订单审核")
    public String audit() {
        Map<String,Object> jsonMap  = new HashMap<>();
        try {
            stockOutcomeBillService.audit(stockOutcomeBill.getId());
            jsonMap.put("seccess",true);
            jsonMap.put("msg","审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("seccess",false);
            jsonMap.put("msg",e.getMessage());
        }
        putJson(jsonMap);
        return NONE;
    }


    @RequiredPermission("出库订单新增/更改")
    public String input() throws Exception {
        putContext("depots", depotService.listAll());
        putContext("clients", clientService.listAll());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("出库订单保存/更新")
    public String saveOrUpdate() {
        try {
            if (stockOutcomeBill.getId() == null) {
                stockOutcomeBillService.save(stockOutcomeBill);
                super.addActionMessage("保存成功");
            } else {
                stockOutcomeBillService.update(stockOutcomeBill);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("出库订单删除")
    public String delete() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBillService.delete(stockOutcomeBill);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            stockOutcomeBill.setDepot(null);
            stockOutcomeBill.setClient(null);
        }
        stockOutcomeBill.getItems().clear();
    }
}
