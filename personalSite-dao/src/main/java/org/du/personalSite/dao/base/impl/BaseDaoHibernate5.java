package org.du.personalSite.dao.base.impl;

import org.du.personalSite.dao.base.BaseDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


/**
 * Created by duqinyuan
 */
public class BaseDaoHibernate5<T> implements BaseDao<T>
{
    @Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}

	public T get(Class<T> entityClazz , Serializable id) throws Exception
	{
		return (T)getSessionFactory().getCurrentSession()
			.get(entityClazz , id);
	}
	public Serializable save(T entity) throws Exception
	{
		return getSessionFactory().getCurrentSession()
			.save(entity);
	}
	public void update(T entity) throws Exception
	{
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}
	public void delete(T entity) throws Exception
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}
	public void delete(Class<T> entityClazz , Serializable id) throws Exception
	{
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName()
				+ " en where en.id = ?0")
			.setParameter("0" , id)
			.executeUpdate();
	}
	public List<T> findAll(Class<T> entityClazz) throws Exception
	{
		return find("select en from "
			+ entityClazz.getSimpleName() + " en");
	}

	public long findCount(Class<T> entityClazz) throws Exception
	{
		List<?> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	protected List<T> find(String hql) throws Exception
	{
		return (List<T>)getSessionFactory().getCurrentSession()
			.createQuery(hql)
			.list();
	}
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql , Object... params) throws Exception
	{
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,
		 int pageNo, int pageSize) throws Exception
	{
		return getSessionFactory().getCurrentSession()
			.createQuery(hql)
			.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql , int pageNo, int pageSize
		, Object... params) throws Exception
	{
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return query.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}
}
