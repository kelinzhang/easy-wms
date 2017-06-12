package com.luis.wms.service;

import com.luis.wms.domain.StockOutcomeBill;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IStockOutcomeBillService {
	void save(StockOutcomeBill entity);

	void update(StockOutcomeBill entity);

	void delete(StockOutcomeBill entity);

	StockOutcomeBill get(Long id);

	List<StockOutcomeBill> listAll();

	PageResult query(QueryObject qo);

	void audit(Long id);
}
