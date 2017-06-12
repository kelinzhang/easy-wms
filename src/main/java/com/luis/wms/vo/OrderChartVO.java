package com.luis.wms.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class OrderChartVO {
    private String groupByName;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;

    public OrderChartVO(String groupByName, BigDecimal totalNumber, BigDecimal totalAmount) {
        this.groupByName = groupByName;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
    }
}
