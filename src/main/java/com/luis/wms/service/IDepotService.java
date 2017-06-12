package com.luis.wms.service;

import com.luis.wms.domain.Depot;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IDepotService {
	void save(Depot entity);

	void update(Depot entity);

	void delete(Depot entity);

	Depot get(Long id);

	List<Depot> listAll();

	PageResult query(QueryObject qo);
}
