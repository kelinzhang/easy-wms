package com.luis.wms.dao.impl;

import com.luis.wms.dao.IRoleDAO;
import com.luis.wms.domain.Role;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class RoleDAOImpl extends GenericDAOImpl<Role> implements IRoleDAO {

	@Override
	public PageResult query(QueryObject qo) {
		String query = qo.getQuery();
		List<Object> params = qo.getParams();
		String hql = "select count(obj) from Role obj " + query;
		Session session = super.getSessionFactory().getCurrentSession();
		Query countQuery = session.createQuery(hql);
		for (int i = 0; i < params.size(); i++) {
			countQuery.setParameter(i, params.get(i));
		}
		Integer totalCount = ((Long) countQuery.uniqueResult()).intValue();
		if (totalCount == 0) {
			return new PageResult(5, 1, 0, new ArrayList(){});
		}
		//----------------------------------------
		hql = "select obj from Role obj " + query;
		Query listQuery = session.createQuery(hql);
		for (int i = 0; i < params.size(); i++) {
			listQuery.setParameter(i, params.get(i));
		}
		List listData = listQuery.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize())
		.setMaxResults(qo.getPageSize()).list();
		return new PageResult(qo.getPageSize(), qo.getCurrentPage(), totalCount, listData);
	}

}
