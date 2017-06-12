package com.luis.wms.dao;

import com.luis.wms.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock>{
    ProductStock getPsByDepotIdAndProId(Long depId, Long proId);
}
