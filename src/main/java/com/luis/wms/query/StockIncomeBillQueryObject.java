package com.luis.wms.query;

import com.luis.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockIncomeBillQueryObject extends QueryObject {

	private Date beginDate;
	private Date endDate;
	private Long depotId = -1L;
	private int status = -1;

	public void customizedQuery() {
		if (beginDate != null) {
			addQuery(" obj.vdate >= ?", DateUtil.getBegin(beginDate));
		}
		if (endDate != null) {
			addQuery(" obj.vdate <= ?", DateUtil.getEnd(endDate));
		}
		if(depotId > 0){
			addQuery(" obj.depot.id = ?",depotId);
		}
		if(status >= 0){
			addQuery(" obj.status = ?",status);
		}
	}
}
