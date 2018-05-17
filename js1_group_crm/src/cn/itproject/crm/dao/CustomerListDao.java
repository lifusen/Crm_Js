package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.db.BaseDao;

public interface CustomerListDao extends BaseDao<Customer>{

	/**
	 * 获取有效客户列表
	 * @param pageIndex
	 * @param pageSize
	 * @param eIds
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex,
			Integer pageSize, Integer[] eIds,List<Integer> states,String keyStr,String fLevel,String cLevel) throws Exception;
	
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex,
			Integer pageSize, Integer[] eIds,List<Integer> states,String keyStr,
			String beginDate,String endDate,String sqlName,String failureMessage,Integer fllowType,String fLevel,String cLevel) throws Exception;
	
	/**
	 * 根据状态和员工id获取客户数量
	 * @param states	状态
	 * @param eIds		员工ids
	 * @param keys		搜索条件
	 * @return
	 * @throws Exception
	 */
	public Integer getCountByteStateAndEId(List<Integer> states,Integer[] eIds,String keys,String fLevel,String cLevel) throws Exception;
	public Integer getCountByteStateAndEId(List<Integer> states,Integer[] eIds,String keys,
			String beginDate,String endDate,String sqlName,String failureMessage,Integer fllowType,String fLevel,String cLevel) throws Exception;
	
	


	public List<Map<String, Object>> srarchList(String sqlName,Integer pageIndex,
			Integer pageSize,Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanIds, String beginDate, String endDate, String orderKey
			,String[] follow,String[] wage,String[] unit,Integer[] vIds) throws Exception;
	
	
	public Integer searchCount(String sqlName,Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanTypes, String beginDate, String endDate
			,String[] follow,String[] wage,String[] unit,Integer[] vIds) throws Exception;

	public List<Integer> getIdsByteStateAndEId(List<Integer> validlist, Integer[] ids, String keyStr, String fLevel,
			String cLevel) throws Exception;
}
