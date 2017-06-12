package com.luis.wms.service;

import com.luis.wms.domain.ProductStock;
import com.luis.wms.domain.StockIncomeBill;
import com.luis.wms.domain.StockOutcomeBill;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IProductStockService {
	void save(ProductStock entity);

	void update(ProductStock entity);

	void delete(ProductStock entity);

	ProductStock get(Long id);

	List<ProductStock> listAll();

	PageResult query(QueryObject qo);

	void stockIncome(StockIncomeBill bill);

	void stockOutcome(StockOutcomeBill bill);

	ProductStock getPsByDepotIdAndProId(Long depId, Long proId);
}
