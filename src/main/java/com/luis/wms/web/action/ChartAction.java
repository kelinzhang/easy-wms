package com.luis.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.luis.wms.query.OrderChartQueryObject;
import com.luis.wms.query.SaleChartQueryObject;
import com.luis.wms.service.IBrandService;
import com.luis.wms.service.IChartService;
import com.luis.wms.service.IClientService;
import com.luis.wms.service.ISupplierService;
import com.luis.wms.vo.OrderChartGroupBy;
import com.luis.wms.vo.SaleChartGroupBy;
import com.luis.wms.vo.SaleChartVO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartAction extends BaseAction{

    @Setter
    private IChartService chartService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IClientService clientService;

    @Getter@Setter
    private OrderChartQueryObject qo = new OrderChartQueryObject();
    @Getter@Setter
    private SaleChartQueryObject sqo = new SaleChartQueryObject();

    public String orderChart(){
        putContext("suppliers",supplierService.listAll());
        putContext("brands",brandService.listAll());
        putContext("result",chartService.queryOrderChart(qo));
        putContext("groupBy", OrderChartGroupBy.values());
        return "orderChart";
    }

    public String saleChart(){
        putContext("clients",clientService.listAll());
        putContext("brands",brandService.listAll());
        putContext("result",chartService.querySaleChart(sqo));
        putContext("groupBy", SaleChartGroupBy.values());
        return "saleChart";
    }

    public String viewByLine(){
        List<String> names = new ArrayList<>();
        List<BigDecimal> values = new ArrayList<>();
        List<BigDecimal> gross = new ArrayList<>();
        for (SaleChartVO vo: chartService.querySaleChart(sqo)) {
            names.add(vo.getGroupByName());
            values.add(vo.getSaleAmount());
            gross.add(vo.getGrossProfit());
        }
        putContext("names", JSON.toJSONString(names));
        putContext("values", JSON.toJSONString(values));
        putContext("gross", JSON.toJSONString(gross));
        putContext("groupBy", SaleChartGroupBy.valueOf(sqo.getGroupType()).getName());
        return "viewByLine";
    }

    public String viewByPie(){
        List<Object[]> list = new ArrayList<>();
        for (SaleChartVO vo : chartService.querySaleChart(sqo)) {
            list.add(new Object[]{vo.getGroupByName(),vo.getSaleAmount()});
        }
        putContext("list", JSON.toJSONString(list));
        putContext("groupBy", SaleChartGroupBy.valueOf(sqo.getGroupType()).getName());
        return "viewByPie";
    }
}

