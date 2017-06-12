package com.luis.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter
public class StockIncomeBill extends BaseDomain{

    public static final int STATE_NORMAL = 0;


    public static final int STATE_AUDIT = 1;

    private String sn;
    private Date vdate;
    private int status = STATE_NORMAL;
    private BigDecimal totalAmount;
    private BigDecimal totalNumber;
    private Date auditTime;
    private Date inputTime;

    private Employee auditor;
    private Employee inputUser;
    private Depot depot;

    private List<StockIncomeBillItem> items = new ArrayList<>();
}
