package com.luis.wms.service.impl;

import com.luis.wms.dao.IProductStockDAO;
import com.luis.wms.domain.*;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IProductStockService;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {

    @Setter
    private IProductStockDAO productStockDAO;

    public void save(ProductStock entity) {
        productStockDAO.save(entity);
    }

    public void update(ProductStock entity) {
        productStockDAO.update(entity);
    }

    public void delete(ProductStock entity) {
        productStockDAO.delete(entity);
    }

    public ProductStock get(Long id) {
        return productStockDAO.get(id);
    }

    public List<ProductStock> listAll() {
        return productStockDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return productStockDAO.query(qo);
    }

    @Override
    public void stockIncome(StockIncomeBill bill) {
        ProductStock ps = null;
        for (StockIncomeBillItem item : bill.getItems()) {
            Long depId = bill.getDepot().getId();
            Long proId = item.getProduct().getId();
            ps = productStockDAO.getPsByDepotIdAndProId(depId,proId);
            if (ps == null) {
                ps = new ProductStock();
                ps.setDepot(bill.getDepot());
                ps.setProduct(item.getProduct());
                ps.setStockNumber(item.getNumber());
                ps.setStockPrice(item.getCostPrice());
                ps.setStockAmount(item.getAmount());
                productStockDAO.save(ps);
            }else{
                ps.setStockAmount(ps.getStockAmount().add(item.getAmount()));
                ps.setStockNumber(ps.getStockNumber().add(item.getNumber()));
                ps.setStockPrice(ps.getStockAmount().divide(ps.getStockNumber(),2, BigDecimal.ROUND_HALF_UP));
                productStockDAO.update(ps);
            }
        }
    }

    @Override
    public void stockOutcome(StockOutcomeBill bill) {
        //查找对应的库存
        ProductStock ps = null;
        for (StockOutcomeBillItem item : bill.getItems()) {
            ps = productStockDAO.getPsByDepotIdAndProId(bill.getDepot().getId(),item.getProduct().getId());
            //库存不存在
            if (ps == null) {
                throw new RuntimeException(bill.getDepot().getName()+"仓库中"+item.getProduct().getName()+"商品不存在");
            }
            //库存存在
            if(ps.getStockNumber().compareTo(item.getNumber()) < 0){
                throw new RuntimeException(bill.getDepot().getName()+"仓库中"+item.getProduct().getName()+"商品库存不足,现有库存为"+ps.getStockNumber());
            }else{
                ps.setStockNumber(ps.getStockNumber().subtract(item.getNumber()));
                ps.setStockAmount(ps.getStockNumber().multiply(ps.getStockPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
                productStockDAO.update(ps);
            }
        }
    }

    @Override
    public ProductStock getPsByDepotIdAndProId(Long depId, Long proId) {
        return productStockDAO.getPsByDepotIdAndProId(depId,proId);
    }
}
