package cn.itproject.crm.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.service.BaseService;

public abstract class BaseServiceSupport<T> implements BaseService<T>{
	protected abstract BaseDao<T> getBaseDao();
	
	@Override
	public Serializable addEntity(T t) throws Exception {
		if (t==null) {
			return null;
		}
		return getBaseDao().save(t);
	}
	
	@Override
	public void batchAddEntity(List<T> list) throws Exception {
		for (T t : list) {
			getBaseDao().save(t);
		}
	}

	@Override
	public void updateEntity(T t) throws Exception {
		getBaseDao().update(t);
	}
	@Override
	public void mergeEntity(T t) throws Exception {
		getBaseDao().merge(t);
	}

	@Override
	public void deleteEntity(Serializable id) throws Exception {
		getBaseDao().remove(id);
	}

	@Override
	public T getEntity(Serializable id) throws Exception {
		return getBaseDao().get(id);
	}
	
	@Override
	public List<T> getEntityAll() throws Exception {
		return getBaseDao().queryList();
	}

	@Override
	public Integer getCount() throws Exception{
		return getBaseDao().count();
	}

	@Override
	public List<T> getEntity(Integer pageIndex, Integer pageSize) throws Exception {
		List<T> list = getBaseDao().queryList(pageIndex, pageSize,null);
		if (list.size()<1 && pageIndex>1) {
			pageIndex = pageIndex-1;
			list = getBaseDao().queryList(pageIndex, pageSize, null);
		}
		return list;
	}
	
	@Override
	public List<T> getEntity(Integer pageIndex, Integer pageSize,LinkedHashMap<String, OrderByType> orderBy) throws Exception {
		List<T> list = getBaseDao().queryList(pageIndex, pageSize,orderBy);
		if (list.size()<1 && pageIndex>1) {
			pageIndex = pageIndex-1;
			list = getBaseDao().queryList(pageIndex, pageSize, orderBy);
		}
		return list;
	}

	@Override
	public void batchDelete(Serializable[] ids) throws Exception{
		for (int i = 0; i < ids.length; i++) {
			getBaseDao().remove(ids[i]);
		}
	}

	@Override
	public void soapUpdateEntity(T t) throws Exception {
		updateEntity(t);
	}
	
	@Override
	public void soapAddEntity(T t) throws Exception {
		addEntity(t);
	}
	
	@Override
	public void batchSaveEntity(List<T> ts) throws Exception {
		batchAddEntity(ts);
	}
	
	@Override
	public void saveEntity(T t) throws Exception {
		addEntity(t);
	}
	
	@Override
	public Boolean isExist(Integer id) throws Exception {
		return getBaseDao().getCount(id)==1;
	}
	
	@Override
	public Boolean isExist(String name) throws Exception {
		return getBaseDao().getCount(name)>=1;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	protected Map<Integer, String> handleListToMapOfIdName(List<Map<String, Object>> list) throws Exception{
		Map<Integer, String> resultMap = new HashMap<>();
		if (list != null) {
			for (Map<String, Object> map : list) {
				Integer id = Integer.parseInt(String.valueOf(map.get("id")));
				String name = String.valueOf(map.get("name"));
				resultMap.put(id, name);
			}
		}
		System.out.println(resultMap);
		return resultMap;
	}
}
