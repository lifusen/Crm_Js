package cn.itproject.crm.db.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.DaoUtil;
import cn.itproject.crm.db.utils.OrderByType;

@SuppressWarnings("unchecked")
public abstract class BaseDaoSupport<T> implements BaseDao<T> {
	private Class<?> entityClass = (Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
			.getActualTypeArguments()[0]);
	@Resource
	private SessionFactory sessionFactory;

	protected Session getSession() throws Exception {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T t) throws Exception {
		if (t == null) {
			return null;
		}
		return getSession().save(t);
	}

	@Override
	public void remove(Serializable id) throws Exception {
		T t = get(id);
		if (t != null) {
			getSession().delete(t);
		}
	}

	@Override
	public void update(T t) throws Exception {
		getSession().update(t);
	}

	@Override
	public void update(String hql, Object... objects) throws Exception {
		Query query = getSession().createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	public void merge(T t) throws Exception {
		getSession().merge(t);
	}

	@Override
	public T get(Serializable id) throws Exception {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public T get(String where, Object... params) throws Exception {
		String hql = "select o from " + entityClass.getSimpleName() + " o"
				+ ((where == null || where.length() == 0) ? "" : " where " + where);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		if (list != null && list.size() > 0) {
			return (T) list.get(0);
		}
		return null;
	}

	@Override
	public Integer count() throws Exception {
		return count(null);
	}

	@Override
	public Integer count(String where, Object... params) throws Exception {
		String hql = "select count(o) from " + entityClass.getSimpleName() + " o"
				+ ((where == null || where.length() == 0) ? "" : " where " + where);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public List<T> queryList() throws Exception {
		return queryList(null);
	}

	@Override
	public List<T> queryList(LinkedHashMap<String, OrderByType> orderBy) throws Exception {
		return queryList(0, 0, orderBy);
	}

	@Override
	public List<T> queryList(String where, LinkedHashMap<String, OrderByType> orderBy, Object... params)
			throws Exception {
		return queryList(0, 0, where, orderBy, params);
	}

	@Override
	public List<T> queryList(Integer pageIndex, Integer pageSize, LinkedHashMap<String, OrderByType> orderBy)
			throws Exception {
		return queryList(pageIndex, pageSize, null, orderBy);
	}

	@Override
	public List<T> queryList(Integer pageIndex, Integer pageSize, String where,
			LinkedHashMap<String, OrderByType> orderBy, Object... params) throws Exception {
		String hql = "select o from " + entityClass.getSimpleName() + " o"
				+ ((where == null || where.length() == 0) ? "" : " where " + where) + DaoUtil.buildOrderBy(orderBy);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		DaoUtil.buildPagination(query, pageIndex, pageSize);
		return query.list();
	}

	@Override
	public Integer getCount(Integer id) throws Exception {
		String hql = "select count(*) from " + entityClass.getSimpleName();
		return Integer.parseInt(String.valueOf(getSession().createQuery(hql).uniqueResult()));
	}

	@Override
	public Integer getCount(String name) throws Exception {
		String hql = "select count(*) from " + entityClass.getSimpleName() + " o where o.name = :name";
		Query query = getSession().createQuery(hql);
		query.setString("name", name);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public List<T> getList(Integer pageIndex, Integer pageSize, String hql, Object... params) throws Exception {
		Query query = getSession().createQuery(hql);
		// 设置参数
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		// 设置分页
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<T> getList(String hql, Object... params) throws Exception {
		Query query = getSession().createQuery(hql);
		// 设置参数
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}
}