package com.luis.wms.query;

import com.luis.wms.domain.OrderBill;
import com.luis.wms.util.DateUtil;
import com.luis.wms.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter@Getter
public class OrderChartQueryObject extends QueryObject {

    private String groupType = "ORDERMAN";
    private Date beginDate;
    private Date endDate;
    private String keyword;
    private Long brandId = -1L;
    private Long supplierId = -1L;

    @Override
    protected void customizedQuery() {
        addQuery("item.bill.status = ?", OrderBill.STATE_AUDIT);
        if (beginDate != null) {
            addQuery("item.bill.vdate >= ?", DateUtil.getBegin(beginDate));
        }
        if (endDate != null) {
            addQuery("item.bill.vdate <= ?", DateUtil.getEnd(endDate));
        }
        if(StringUtil.hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery(" (item.product.name like ? or item.product.sn like ?)",key,key);
        }
        if(brandId > 0){
            addQuery("item.product.brand.id = ?",brandId);
        }
        if(supplierId > 0){
            addQuery("item.bill.supplier.id = ?",supplierId);
        }
    }
}
