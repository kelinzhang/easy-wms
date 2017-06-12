package com.luis.wms.query;

import com.luis.wms.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
	private String keyword;
	private Long depotId = -1L;
	private Long brandId = -1L;
	private BigDecimal limitNumber;

	public void customizedQuery() {
		if(StringUtil.hasLength(keyword)){
			String key = "%"+keyword+"%";
			addQuery(" (obj.product.name like ? or obj.product.sn like ?)",key,key);
		}
		if(depotId > 0){
			addQuery(" obj.depot.id = ?",depotId);
		}
		if(brandId > 0){
			addQuery(" obj.product.brand.id = ?",brandId);
		}
		if (limitNumber != null) {
			addQuery("obj.stockNumber <= ?",limitNumber);
		}
	}
}
