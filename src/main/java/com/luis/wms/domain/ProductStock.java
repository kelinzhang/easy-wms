package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class ProductStock extends BaseDomain{
    private Depot depot;
    private Product product;
    private BigDecimal stockNumber;
    private BigDecimal stockPrice;
    private BigDecimal StockAmount;
}
