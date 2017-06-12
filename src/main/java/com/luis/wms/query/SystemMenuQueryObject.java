package com.luis.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject {
	private Long parentId = -1L;
	private String ParentSn;

	public void customizedQuery() {
		if (parentId > 0){
			addQuery("obj.parent.id = ?",parentId);
		} else {
			addQuery("obj.parent is null");
		}
	}
}
