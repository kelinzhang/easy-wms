package com.luis.wms.service.impl;

import com.luis.wms.dao.IProductDAO;
import com.luis.wms.domain.Product;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IProductService;
import lombok.Setter;

import java.util.List;

public class ProductServiceImpl implements IProductService {

    @Setter
    private IProductDAO productDAO;

    public void save(Product entity) {
        productDAO.save(entity);
    }

    public void update(Product entity) {
        productDAO.update(entity);
    }

    public void delete(Product entity) {
        productDAO.delete(entity);
    }

    public Product get(Long id) {
        return productDAO.get(id);
    }

    public List<Product> listAll() {
        return productDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return productDAO.query(qo);
    }
}
