package com.luis.wms.vo;

import lombok.Getter;

@Getter
public enum OrderChartGroupBy {
    ORDERMAN(" item.bill.inputUser.name "," item.bill.inputUser ","订货人员"),
    PRODUCT(" item.product.name "," item.product ","商品名称"),
    BRAND(" item.product.brand.name "," item.product.brand ","品牌名称"),
    SUPPLIER(" item.bill.supplier.name "," item.bill.supplier ","供应商"),
    MONTH(" DATE_FORMAT(item.bill.vdate,'%Y-%m') "," DATE_FORMAT(item.bill.vdate,'%Y-%m') ","订货日期(月)"),
    DAY(" DATE_FORMAT(item.bill.vdate,'%Y-%m-%d') "," DATE_FORMAT(item.bill.vdate,'%Y-%m-%d') ","订货日期(日)");
    private String groupByName;
    private String groupByType;
    private String name;
    OrderChartGroupBy(String groupByName,String groupByType,String name){
        this.groupByName = groupByName;
        this.groupByType = groupByType;
        this.name = name;
    }
}
