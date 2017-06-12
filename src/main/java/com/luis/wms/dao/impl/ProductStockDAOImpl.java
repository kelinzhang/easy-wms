package com.luis.wms.dao.impl;

import com.luis.wms.dao.IProductStockDAO;
import com.luis.wms.domain.ProductStock;
import org.hibernate.Session;

public class ProductStockDAOImpl extends GenericDAOImpl<ProductStock> implements IProductStockDAO {

    @Override
    public ProductStock getPsByDepotIdAndProId(Long depId, Long proId) {
        Session session = super.getSessionFactory().getCurrentSession();
        String hql = "select ps from ProductStock ps where ps.product.id = ? and ps.depot.id = ?";
        ProductStock ps = (ProductStock) session.createQuery(hql).setParameter(0,proId).setParameter(1,depId).uniqueResult();
        return ps;
    }
}
