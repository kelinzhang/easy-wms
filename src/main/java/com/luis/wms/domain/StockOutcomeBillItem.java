package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class StockOutcomeBillItem extends BaseDomain{
    private BigDecimal salePrice;
    private BigDecimal amount;
    private BigDecimal number;
    private String remark;

    private Product product;
    private StockOutcomeBill bill;
}
