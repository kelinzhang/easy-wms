package com.luis.wms.dao.impl;

import com.luis.wms.dao.IGenericDAO;
import com.luis.wms.query.PageResult;
import com.luis.wms.query.QueryObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
abstract public class GenericDAOImpl<T> implements IGenericDAO<T> {

	//获取泛型T的真实数据类型
	private Class<T> entityType;
	public GenericDAOImpl(){
		ParameterizedType pType = (ParameterizedType)this.getClass().getGenericSuperclass();
		entityType = (Class<T>) pType.getActualTypeArguments()[0];
	}
	
	@Setter@Getter
	private SessionFactory sessionFactory;
	
	@Override
	public void save(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	@Override
	public void delete(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}

	@Override
	public void update(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public T get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(entityType, id);
	}

	@Override
	public List<T> listAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("select obj from "+entityType.getSimpleName()+" obj").list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		String query = qo.getQuery();
		List<Object> params = qo.getParams();
		String hql = "select count(obj) from "+entityType.getSimpleName()+" obj " + query;
		Session session = sessionFactory.getCurrentSession();
		Query countQuery = session.createQuery(hql);
		for (int i = 0; i < params.size(); i++) {
			countQuery.setParameter(i, params.get(i));
		}
		Integer totalCount = ((Long) countQuery.uniqueResult()).intValue();
		if (totalCount == 0) {
			return new PageResult(5, 1, 0, new ArrayList(){});
		}
		//----------------------------------------
		hql = "select obj from "+entityType.getSimpleName()+" obj " + query;
		Query listQuery = session.createQuery(hql);
		for (int i = 0; i < params.size(); i++) {
			listQuery.setParameter(i, params.get(i));
		}
		List listData = listQuery.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize())
				.setMaxResults(qo.getPageSize()).list();
		return new PageResult(qo.getPageSize(), qo.getCurrentPage(), totalCount, listData);
	}

	public void batchDelete(List<Long> ids){
		String hql = "delete from "+entityType.getSimpleName()+" obj where obj.id in(:ids)";
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
	}
}
