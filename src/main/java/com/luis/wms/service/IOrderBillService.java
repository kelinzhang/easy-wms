package com.luis.wms.service;

import com.luis.wms.domain.OrderBill;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;

import java.util.List;

public interface IOrderBillService {
	void save(OrderBill entity);

	void update(OrderBill entity);

	void delete(OrderBill entity);

	OrderBill get(Long id);

	List<OrderBill> listAll();

	PageResult query(QueryObject qo);

    void audit(Long id);
}
