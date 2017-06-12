package com.luis.wms.vo;

import lombok.Getter;

@Getter
public enum SaleChartGroupBy {
    SALEMAN(" sa.saleman.name "," sa.saleman ","销售人员"),
    PRODUCT(" sa.product.name "," sa.product ","商品名称"),
    BRAND(" sa.product.brand.name "," sa.product.brand ","品牌名称"),
    CLIENT(" sa.client.name "," sa.client ","客户"),
    MONTH(" DATE_FORMAT(sa.vdate,'%Y-%m') "," DATE_FORMAT(sa.vdate,'%Y-%m') ","订货日期(月)"),
    DAY(" DATE_FORMAT(sa.vdate,'%Y-%m-%d') "," DATE_FORMAT(sa.vdate,'%Y-%m-%d') ","订货日期(日)");
    private String groupByName;
    private String groupByType;
    private String name;
    SaleChartGroupBy(String groupByName, String groupByType, String name){
        this.groupByName = groupByName;
        this.groupByType = groupByType;
        this.name = name;
    }
}
