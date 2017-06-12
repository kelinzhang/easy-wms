package com.luis.wms.service.impl;

import com.luis.wms.dao.ISupplierDAO;
import com.luis.wms.domain.Supplier;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.ISupplierService;
import lombok.Setter;

import java.util.List;

public class SupplierServiceImpl implements ISupplierService {

    @Setter
    private ISupplierDAO supplierDAO;

    public void save(Supplier entity) {
        supplierDAO.save(entity);
    }

    public void update(Supplier entity) {
        supplierDAO.update(entity);
    }

    public void delete(Supplier entity) {
        supplierDAO.delete(entity);
    }

    public Supplier get(Long id) {
        return supplierDAO.get(id);
    }

    public List<Supplier> listAll() {
        return supplierDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return supplierDAO.query(qo);
    }
}
