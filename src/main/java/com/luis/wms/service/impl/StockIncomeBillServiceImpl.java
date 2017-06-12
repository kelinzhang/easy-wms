package com.luis.wms.service.impl;

import com.luis.wms.dao.IStockIncomeBillDAO;
import com.luis.wms.domain.StockIncomeBill;
import com.luis.wms.domain.StockIncomeBillItem;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IProductStockService;
import com.luis.wms.service.IStockIncomeBillService;
import com.luis.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {

    @Setter
    private IStockIncomeBillDAO stockIncomeBillDAO;
    @Setter
    private IProductStockService productStockService;

    public void save(StockIncomeBill bill) {
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        parseItem(bill);
        stockIncomeBillDAO.save(bill);
    }

    private void parseItem(StockIncomeBill bill) {
        bill.setStatus(StockIncomeBill.STATE_NORMAL);
        bill.setTotalAmount(BigDecimal.ZERO);
        bill.setTotalNumber(BigDecimal.ZERO);
        for (StockIncomeBillItem item : bill.getItems()) {
            item.setBill(bill);
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }

    public void update(StockIncomeBill bill) {
        StockIncomeBill old = stockIncomeBillDAO.get(bill.getId());
        if(old.getStatus() == StockIncomeBill.STATE_AUDIT){
            return;
        }
        parseItem(bill);
        stockIncomeBillDAO.update(bill);
    }

    public void delete(StockIncomeBill bill) {
        bill = stockIncomeBillDAO.get(bill.getId());
        if(bill.getStatus() == StockIncomeBill.STATE_AUDIT){
            return;
        }
        stockIncomeBillDAO.delete(bill);
    }

    public StockIncomeBill get(Long id) {
        return stockIncomeBillDAO.get(id);
    }

    public List<StockIncomeBill> listAll() {
        return stockIncomeBillDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return stockIncomeBillDAO.query(qo);
    }

    @Override
    public void audit(Long id) {
        StockIncomeBill bill = stockIncomeBillDAO.get(id);
        if(bill.getStatus() == StockIncomeBill.STATE_AUDIT){
            return;
        }
        bill.setAuditor(UserContext.getCurrentUser());
        bill.setAuditTime(new Date());
        bill.setStatus(StockIncomeBill.STATE_AUDIT);
        productStockService.stockIncome(bill);
        stockIncomeBillDAO.update(bill);
    }
}
