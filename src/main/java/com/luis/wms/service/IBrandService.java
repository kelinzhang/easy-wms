package com.luis.wms.service;

import com.luis.wms.domain.Brand;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IBrandService {
	void save(Brand entity);

	void update(Brand entity);

	void delete(Brand entity);

	Brand get(Long id);

	List<Brand> listAll();

	PageResult query(QueryObject qo);
}
