package com.luis.wms.service.impl;

import com.luis.wms.dao.IBrandDAO;
import com.luis.wms.domain.Brand;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IBrandService;
import lombok.Setter;

import java.util.List;

public class BrandServiceImpl implements IBrandService {

    @Setter
    private IBrandDAO brandDAO;

    public void save(Brand entity) {
        brandDAO.save(entity);
    }

    public void update(Brand entity) {
        brandDAO.update(entity);
    }

    public void delete(Brand entity) {
        brandDAO.delete(entity);
    }

    public Brand get(Long id) {
        return brandDAO.get(id);
    }

    public List<Brand> listAll() {
        return brandDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return brandDAO.query(qo);
    }
}
