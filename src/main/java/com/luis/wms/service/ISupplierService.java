package com.luis.wms.service;

import com.luis.wms.domain.Supplier;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface ISupplierService {
	void save(Supplier entity);

	void update(Supplier entity);

	void delete(Supplier entity);

	Supplier get(Long id);

	List<Supplier> listAll();

	PageResult query(QueryObject qo);
}
