package com.luis.wms.web.action;

import com.luis.wms.query.QueryObject;
import com.luis.wms.query.ProductStockQueryObject;
import com.luis.wms.service.IBrandService;
import com.luis.wms.service.IDepotService;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.ProductStock;
import com.luis.wms.service.IProductStockService;
import com.luis.wms.util.RequiredPermission;

public class ProductStockAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductStockService productStockService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IBrandService brandService;

    @Getter
    private ProductStock productStock = new ProductStock();

    @Getter
    private QueryObject qo = new ProductStockQueryObject();

    @RequiredPermission("即时库存列表")
    public String execute() throws Exception {
        try {
            putContext("brands",brandService.listAll());
            putContext("depots",depotService.listAll());
            putContext("pageResult", productStockService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }
}
