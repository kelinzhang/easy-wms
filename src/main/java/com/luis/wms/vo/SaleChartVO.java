package com.luis.wms.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class SaleChartVO {
    private String groupByName;
    private BigDecimal saleNumber;
    private BigDecimal saleAmount;
    private BigDecimal grossProfit;

    public SaleChartVO(String groupByName, BigDecimal saleNumber, BigDecimal saleAmount, BigDecimal grossProfit) {
        this.groupByName = groupByName;
        this.saleNumber = saleNumber;
        this.saleAmount = saleAmount;
        this.grossProfit = grossProfit;
    }
}
