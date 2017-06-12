package com.luis.wms.service.impl;

import com.luis.wms.dao.IClientDAO;
import com.luis.wms.domain.Client;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IClientService;
import lombok.Setter;

import java.util.List;

public class ClientServiceImpl implements IClientService {

    @Setter
    private IClientDAO clientDAO;

    public void save(Client entity) {
        clientDAO.save(entity);
    }

    public void update(Client entity) {
        clientDAO.update(entity);
    }

    public void delete(Client entity) {
        clientDAO.delete(entity);
    }

    public Client get(Long id) {
        return clientDAO.get(id);
    }

    public List<Client> listAll() {
        return clientDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return clientDAO.query(qo);
    }
}
