package com.luis.wms.service.impl;

import com.luis.wms.dao.IStockOutcomeBillDAO;
import com.luis.wms.domain.StockOutcomeBill;
import com.luis.wms.domain.StockOutcomeBillItem;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IProductStockService;
import com.luis.wms.service.ISaleAccountService;
import com.luis.wms.service.IStockOutcomeBillService;
import com.luis.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {

    @Setter
    private IStockOutcomeBillDAO stockOutcomeBillDAO;
    @Setter
    private IProductStockService productStockService;
    @Setter
    private ISaleAccountService saleAccountService;

    public void save(StockOutcomeBill bill) {
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        parseItem(bill);
        stockOutcomeBillDAO.save(bill);
    }

    private void parseItem(StockOutcomeBill bill){
        bill.setStatus(StockOutcomeBill.STATE_NORMAL);
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (StockOutcomeBillItem item : bill.getItems()) {
            item.setBill(bill);
            item.setAmount(item.getSalePrice().multiply(item.getNumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }

    public void update(StockOutcomeBill bill) {
        StockOutcomeBill old = stockOutcomeBillDAO.get(bill.getId());
        if(old.getStatus() == StockOutcomeBill.STATE_AUDIT){
            return;
        }
        parseItem(bill);
        stockOutcomeBillDAO.update(bill);
    }

    public void delete(StockOutcomeBill bill) {
        bill = stockOutcomeBillDAO.get(bill.getId());
        if(bill.getStatus() == StockOutcomeBill.STATE_AUDIT){
            return;
        }
        stockOutcomeBillDAO.delete(bill);
    }

    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillDAO.get(id);
    }

    public List<StockOutcomeBill> listAll() {
        return stockOutcomeBillDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return stockOutcomeBillDAO.query(qo);
    }

    @Override
    public void audit(Long id) {
        StockOutcomeBill bill = stockOutcomeBillDAO.get(id);
        if(bill.getStatus() == StockOutcomeBill.STATE_AUDIT){
            return;
        }
        bill.setAuditor(UserContext.getCurrentUser());
        bill.setAuditTime(new Date());
        bill.setStatus(StockOutcomeBill.STATE_AUDIT);
        //处理库存
        productStockService.stockOutcome(bill);
        //生成销售帐
        saleAccountService.addSaleAccount(bill);
        stockOutcomeBillDAO.update(bill);
    }
}
