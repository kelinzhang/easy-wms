package com.luis.wms.service;

import com.luis.wms.domain.SaleAccount;
import com.luis.wms.domain.StockOutcomeBill;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface ISaleAccountService {
	void save(SaleAccount entity);

	void update(SaleAccount entity);

	void delete(SaleAccount entity);

	SaleAccount get(Long id);

	List<SaleAccount> listAll();

	PageResult query(QueryObject qo);

    void addSaleAccount(StockOutcomeBill bill);
}
