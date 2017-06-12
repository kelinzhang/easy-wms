package com.luis.wms.service.impl;

import com.luis.wms.dao.IChartDAO;
import com.luis.wms.query.OrderChartQueryObject;
import com.luis.wms.query.SaleChartQueryObject;
import com.luis.wms.service.IChartService;
import com.luis.wms.vo.OrderChartVO;
import com.luis.wms.vo.SaleChartVO;
import lombok.Setter;

import java.util.List;

public class ChartServiceImpl implements IChartService{

    @Setter
    private IChartDAO chartDAO;

    @Override
    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        return chartDAO.queryOrderChart(qo);
    }

    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        return chartDAO.querySaleChart(qo);
    }
}
