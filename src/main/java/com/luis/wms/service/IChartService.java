package com.luis.wms.service;

import com.luis.wms.query.OrderChartQueryObject;
import com.luis.wms.query.SaleChartQueryObject;
import com.luis.wms.vo.OrderChartVO;
import com.luis.wms.vo.SaleChartVO;

import java.util.List;

public interface IChartService {
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);
    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}
