package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter
public class SaleAccount extends BaseDomain{
    private Date vdate;//销售日期
    private Employee saleman;//销售人员
    private Client client;//客户
    private Depot depot;//仓库
    private Product product;//产品
    private BigDecimal saleNumber;//销售数量
    private BigDecimal salePrice;//销售价格
    private BigDecimal saleAmount;//销售金额
    private BigDecimal costPrice;//成本价格
    private BigDecimal costAmount;//成本金额
}
