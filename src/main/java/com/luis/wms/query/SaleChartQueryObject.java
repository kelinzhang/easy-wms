package com.luis.wms.query;

import com.luis.wms.util.DateUtil;
import com.luis.wms.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter@Getter
public class SaleChartQueryObject extends QueryObject {

    private String groupType = "SALEMAN";
    private Date beginDate;
    private Date endDate;
    private String keyword;
    private Long brandId = -1L;
    private Long clientId = -1L;

    @Override
    protected void customizedQuery() {
        if (beginDate != null) {
            addQuery(" sa.vdate >= ? ", DateUtil.getBegin(beginDate));
        }
        if (endDate != null) {
            addQuery(" sa.vdate <= ? ", DateUtil.getEnd(endDate));
        }
        if(StringUtil.hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery(" (sa.product.name like ? or sa.product.sn like ?) ",key,key);
        }
        if(brandId > 0){
            addQuery(" sa.product.brand.id = ?",brandId);
        }
        if(clientId > 0){
            addQuery(" sa.client.id = ? ",clientId);
        }
    }
}
