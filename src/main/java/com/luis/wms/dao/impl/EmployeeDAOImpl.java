package com.luis.wms.dao.impl;

import com.luis.wms.dao.IEmployeeDAO;
import com.luis.wms.domain.Employee;
import com.luis.wms.query.EmployeeQueryObject;
import com.luis.wms.query.PageResult;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements IEmployeeDAO {

	@Override
	public PageResult query(EmployeeQueryObject qo) {
		String query = qo.getQuery();
		List<Object> params = qo.getParams();
		String hql = "select count(obj) from Employee obj " + query;
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
		hql = "select obj from Employee obj " + query;
		Query listQuery = session.createQuery(hql);
		for (int i = 0; i < params.size(); i++) {
			listQuery.setParameter(i, params.get(i));
		}
		List listData = listQuery.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize())
		.setMaxResults(qo.getPageSize()).list();
		return new PageResult(qo.getPageSize(), qo.getCurrentPage(), totalCount, listData);
	}

	@Override
	public Employee queryForObject(String username, String password) {
		String hql = "select e from Employee e where e.name = ? and e.password = ?";
		Session session = super.getSessionFactory().getCurrentSession();
		return (Employee) session.createQuery(hql).setParameter(0, username).setParameter(1, password).uniqueResult();
	}

}
