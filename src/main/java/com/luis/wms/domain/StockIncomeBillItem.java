package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class StockIncomeBillItem extends BaseDomain{
    private BigDecimal costPrice;
    private BigDecimal amount;
    private BigDecimal number;
    private String remark;

    private Product product;
    private StockIncomeBill bill;
}
