package com.luis.wms.service.impl;

import com.luis.wms.dao.IDepartmentDAO;
import com.luis.wms.domain.Department;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    @Setter
    private IDepartmentDAO departmentDAO;

    @Override
    public void save(Department entity) {
        departmentDAO.save(entity);
    }

    @Override
    public void delete(Department entity) {
        departmentDAO.delete(entity);
    }

    @Override
    public void update(Department entity) {
        departmentDAO.update(entity);
    }

    @Override
    public Department get(Long id) {
        return departmentDAO.get(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return departmentDAO.query(qo);
    }

}
