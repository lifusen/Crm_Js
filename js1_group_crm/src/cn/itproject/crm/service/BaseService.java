package cn.itproject.crm.service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itproject.crm.db.utils.OrderByType;




public interface BaseService<T>{
	/**
	 * 添加实体
	 * @param t
	 */
	public Serializable addEntity(T t) throws Exception;

	/**
	 * soap接口添加实体
	 * @param t
	 * @throws Exception
	 */
	public void soapAddEntity(T t) throws Exception;
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void batchAddEntity(List<T> list) throws Exception;
	
	/**
	 * 修改实体
	 * @param t
	 */
	public void updateEntity(T t) throws Exception;
	/**
	 * 修改实体
	 * @param t
	 */
	public void mergeEntity(T t) throws Exception;
	
	/**
	 * soap接口修改实体
	 * @param t
	 * @throws Exception
	 */
	public void soapUpdateEntity(T t) throws Exception;
	
	/**
	 * 删除实体
	 * @param id 实体ID
	 */
	public void deleteEntity(Serializable id) throws Exception;
	
	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public T getEntity(Serializable id) throws Exception;
	
	/**
	 * 获取实体集合
	 * @return
	 */
	public List<T> getEntityAll() throws Exception;
	
	/**
	 * 获取总记录行
	 * @return
	 */
	public Integer getCount() throws Exception;
	
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<T> getEntity(Integer pageIndex,Integer pageSize) throws Exception;
	
	/**
	 * 分页加排序
	 * @param pageIndex
	 * @param pageSize
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public List<T> getEntity(Integer pageIndex, Integer pageSize,LinkedHashMap<String, OrderByType> orderBy) throws Exception;
	/**
	 * 批量删除
	 * @param ids
	 */
	public void batchDelete(Serializable[] ids) throws Exception;
	
	/**
	 * 初始化
	 * @param ts
	 * @throws Exception
	 */
	public void batchSaveEntity(List<T> ts) throws Exception;
	
	/**
	 * 初始化
	 * @param t
	 * @throws Exception
	 */
	public void saveEntity(T t) throws Exception;
	
	/**
	 * 根据ID判断是否存在行记录
	 * @param id id字段
	 * @return true:存在/false:不存在
	 * @throws Exception
	 */
	public Boolean isExist(Integer id) throws Exception;
	
	/**
	 * 检查name字段是否存在
	 * @param name name字段
	 * @return true:存在/false:不存在
	 * @throws Exception
	 */
	public Boolean isExist(String name) throws Exception;
}