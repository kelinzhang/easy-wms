package com.luis.wms.service;

import com.luis.wms.domain.StockIncomeBill;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IStockIncomeBillService {
	void save(StockIncomeBill entity);

	void update(StockIncomeBill entity);

	void delete(StockIncomeBill entity);

	StockIncomeBill get(Long id);

	List<StockIncomeBill> listAll();

	PageResult query(QueryObject qo);

    void audit(Long id);
}
