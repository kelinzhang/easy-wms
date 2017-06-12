package com.luis.wms.service;

import com.luis.wms.domain.Client;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IClientService {
	void save(Client entity);

	void update(Client entity);

	void delete(Client entity);

	Client get(Long id);

	List<Client> listAll();

	PageResult query(QueryObject qo);
}
