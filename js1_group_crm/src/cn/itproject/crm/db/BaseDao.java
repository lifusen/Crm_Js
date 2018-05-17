package cn.itproject.crm.db;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.db.utils.OrderByType;

public interface BaseDao<T> {
	
	/**
	 * 保存对象
	 * @param t
	 */
	public Serializable save(T t) throws Exception;
	
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void remove(Serializable id) throws Exception;
	
	/**
	 * 修改对象
	 * @param t
	 */
	public void update(T t) throws Exception;
	
	/**
	 * 根据HQL语句修改对象
	 * @param hql
	 * @param objects
	 * @throws Exception
	 */
	public void update(String hql,Object...objects) throws Exception;
	/**
	 * 修改对象
	 * @param t
	 */
	public void merge(T t) throws Exception;

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public T get(Serializable id) throws Exception;
	
	/**
	 * 根据where条件获取对象
	 * @param where
	 * @param params
	 * @return
	 */
	public T get(String where, Object... params) throws Exception;
	
	/**
	 * 获取所有记录总数
	 * @return
	 */
	public Integer count() throws Exception;
	
	/**
	 * 根据where条件获取记录总数
	 * @param where
	 * @param params
	 * @return
	 */
	public Integer count(String where, Object... params) throws Exception;
	
	/**
	 * 查询记录集合
	 * @return
	 */
	public List<T> queryList() throws Exception;
	
	/**
	 * 查询记录集合并排序
	 * @return
	 */
	public List<T> queryList(LinkedHashMap<String, OrderByType> orderBy) throws Exception;
	
	/**
	 * 根据条件获取记录集合
	 * @param where
	 * @param params
	 * @return
	 */
	public List<T> queryList(String where,LinkedHashMap<String, OrderByType> orderBy, Object... params) throws Exception;
	
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<T> queryList(Integer pageIndex, Integer pageSize,LinkedHashMap<String, OrderByType> orderBy) throws Exception;
	/**
	 * 根据条件分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @param where
	 * @param params
	 * @return
	 */
	public List<T> queryList(Integer pageIndex, Integer pageSize, String where, LinkedHashMap<String, OrderByType> orderBy,Object... params) throws Exception;

	/**
	 * 根据ID获取对应的行记录数
	 * @param id
	 * @return
	 */
	public Integer getCount(Integer id) throws Exception;

	/**
	 * 获取name字段对应的行记录数
	 * @param name
	 * @return
	 */
	public Integer getCount(String name) throws Exception;
	
	
	/**
	 * 根据hql查询分页列表
	 * @param pageIndex 页码
	 * @param pageSize 也大小
	 * @param hql HQL语句
	 * @param params 参数
	 * @return 对象的分页列表
	 */
	public List<T> getList(Integer pageIndex,Integer pageSize,String hql, Object...params) throws Exception;
	
	/**
	 * 根据HQL获取列表
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<T> getList(String hql,Object...params) throws Exception;
}