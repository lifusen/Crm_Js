package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.SignCustomerTurn;
import cn.itproject.crm.db.BaseDao;

public interface WarranterTurnDao extends BaseDao<SignCustomerTurn> {

	
	public List<Map<String, Object>> getSignCustomersByEId(Integer pageIndex, Integer pageSize, 
			Integer[] ids, String keyStr) throws Exception;

	public Integer getCountByEId(Integer[] ids, String keyStr) throws Exception;

	public List<Integer> getIdsByEId(Integer[] ids, String keyStr) throws Exception;

}
