package com.luis.wms.service.impl;

import com.luis.wms.dao.IDepotDAO;
import com.luis.wms.domain.Depot;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IDepotService;
import lombok.Setter;

import java.util.List;

public class DepotServiceImpl implements IDepotService {

    @Setter
    private IDepotDAO depotDAO;

    public void save(Depot entity) {
        depotDAO.save(entity);
    }

    public void update(Depot entity) {
        depotDAO.update(entity);
    }

    public void delete(Depot entity) {
        depotDAO.delete(entity);
    }

    public Depot get(Long id) {
        return depotDAO.get(id);
    }

    public List<Depot> listAll() {
        return depotDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return depotDAO.query(qo);
    }
}
