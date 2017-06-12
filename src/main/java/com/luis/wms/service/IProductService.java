package com.luis.wms.service;

import com.luis.wms.domain.Product;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IProductService {
	void save(Product entity);

	void update(Product entity);

	void delete(Product entity);

	Product get(Long id);

	List<Product> listAll();

	PageResult query(QueryObject qo);
}
