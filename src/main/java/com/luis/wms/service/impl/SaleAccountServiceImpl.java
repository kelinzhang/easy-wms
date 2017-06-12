package com.luis.wms.service.impl;

import com.luis.wms.dao.ISaleAccountDAO;
import com.luis.wms.domain.ProductStock;
import com.luis.wms.domain.SaleAccount;
import com.luis.wms.domain.StockOutcomeBill;
import com.luis.wms.domain.StockOutcomeBillItem;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IProductStockService;
import com.luis.wms.service.ISaleAccountService;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class SaleAccountServiceImpl implements ISaleAccountService {

    @Setter
    private ISaleAccountDAO saleAccountDAO;
    @Setter
    private IProductStockService productStockService;

    public void save(SaleAccount entity) {
        saleAccountDAO.save(entity);
    }

    public void update(SaleAccount entity) {
        saleAccountDAO.update(entity);
    }

    public void delete(SaleAccount entity) {
        saleAccountDAO.delete(entity);
    }

    public SaleAccount get(Long id) {
        return saleAccountDAO.get(id);
    }

    public List<SaleAccount> listAll() {
        return saleAccountDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return saleAccountDAO.query(qo);
    }

    @Override
    public void addSaleAccount(StockOutcomeBill bill) {
        //新建销售帐
        SaleAccount sa = null;
        ProductStock ps = null;
        for (StockOutcomeBillItem item : bill.getItems()) {
            sa = new SaleAccount();
            //设置时间
            sa.setVdate(bill.getVdate());
            //设置销售人员
            sa.setSaleman(bill.getInputUser());
            //设置客户
            sa.setClient(bill.getClient());
            //设置仓库
            sa.setDepot(bill.getDepot());
            //设置商品
            sa.setProduct(item.getProduct());
            //设置销售数量
            sa.setSaleNumber(item.getNumber());
            //设置销售价格
            sa.setSalePrice(item.getSalePrice());
            //设置销售总金额
            sa.setSaleAmount(item.getAmount());
            //获取对应的库存
            ps = productStockService.getPsByDepotIdAndProId(bill.getDepot().getId(),item.getProduct().getId());
            //设置成本价格
            sa.setCostPrice(ps.getStockPrice());
            //设置成本总金额
            sa.setCostAmount(sa.getCostPrice().multiply(sa.getSaleNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            saleAccountDAO.save(sa);
        }
    }
}
