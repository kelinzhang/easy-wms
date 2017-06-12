package com.luis.wms.service.impl;

import com.luis.wms.dao.IOrderBillDAO;
import com.luis.wms.domain.OrderBill;
import com.luis.wms.domain.OrderBillItem;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IOrderBillService;
import com.luis.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBillServiceImpl implements IOrderBillService {

    @Setter
    private IOrderBillDAO orderBillDAO;

    public void save(OrderBill bill) {
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        parseItem(bill);
        orderBillDAO.save(bill);
    }

    private void parseItem(OrderBill bill) {
        bill.setStatus(OrderBill.STATE_NORMAL);
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (OrderBillItem item : bill.getItems()) {
            item.setBill(bill);
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }

    public void update(OrderBill bill) {
        OrderBill old = orderBillDAO.get(bill.getId());
        if(old.getStatus() == OrderBill.STATE_AUDIT){
            return;
        }
        parseItem(bill);
        orderBillDAO.update(bill);
    }

    public void delete(OrderBill bill) {
        bill = orderBillDAO.get(bill.getId());
        if(bill.getStatus() == OrderBill.STATE_AUDIT){
            return;
        }
        orderBillDAO.delete(bill);
    }

    public OrderBill get(Long id) {
        return orderBillDAO.get(id);
    }

    public List<OrderBill> listAll() {
        return orderBillDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return orderBillDAO.query(qo);
    }

    @Override
    public void audit(Long id) {
        OrderBill bill = orderBillDAO.get(id);
        if (bill != null && bill.getStatus() == OrderBill.STATE_NORMAL) {
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setAuditTime(new Date());
            bill.setStatus(OrderBill.STATE_AUDIT);
            orderBillDAO.update(bill);
        }
    }
}
