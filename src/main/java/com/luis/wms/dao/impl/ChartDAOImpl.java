package com.luis.wms.dao.impl;


import com.luis.wms.dao.IChartDAO;
import com.luis.wms.query.OrderChartQueryObject;
import com.luis.wms.query.SaleChartQueryObject;
import com.luis.wms.vo.OrderChartGroupBy;
import com.luis.wms.vo.OrderChartVO;
import com.luis.wms.vo.SaleChartGroupBy;
import com.luis.wms.vo.SaleChartVO;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChartDAOImpl implements IChartDAO {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        OrderChartGroupBy obj = OrderChartGroupBy.valueOf(qo.getGroupType());
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder(80);
        sb.append("select new OrderChartVO( ")
                .append(obj.getGroupByName())
                .append(" ,sum(item.number),sum(item.amount)) from OrderBillItem item ")
                .append(qo.getQuery())
                .append(" group by ")
                .append(obj.getGroupByType());
        Query query = session.createQuery(sb.toString());
        for (int i = 0; i < qo.getParams().size() ; i++) {
            query.setParameter(i,qo.getParams().get(i));
        }
        return query.list();
    }

    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        SaleChartGroupBy obj = SaleChartGroupBy.valueOf(qo.getGroupType());
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder(80);
        sb.append("select new com.luis.wms.vo.SaleChartVO( ")
                .append(obj.getGroupByName())
                .append(" ,sum(sa.saleNumber),sum(sa.saleAmount),sum(sa.saleAmount - sa.costAmount)) from SaleAccount sa ")
                .append(qo.getQuery())
                .append(" group by ")
                .append(obj.getGroupByType());
        Query query = session.createQuery(sb.toString());
        for (int i = 0; i < qo.getParams().size() ; i++) {
            query.setParameter(i,qo.getParams().get(i));
        }
        return query.list();
    }
}
