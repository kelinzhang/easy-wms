package com.luis.wms.query;

import com.luis.wms.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject {

	private String keyword;
	private Long brandId = -1L;

	public void customizedQuery() {
		if(StringUtil.hasLength(keyword)){
			String key = "%"+keyword+"%";
			addQuery("(obj.name like ? or obj.sn like ?)",key,key);
		}
		if(brandId > 0){
			addQuery("obj.brand.id = ?",brandId);
		}
	}
}
