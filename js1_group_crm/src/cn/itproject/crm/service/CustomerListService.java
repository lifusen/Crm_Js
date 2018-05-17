package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Customer;
/**
 * 客户列表Service
 * @author ham
 *
 */
public interface CustomerListService extends BaseService<Customer>{

	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex,
			Integer pageSize, Integer[] eIds,List<Integer> states, String keyStr) throws Exception;
	
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex,
			Integer pageSize, Integer[] eIds,List<Integer> states, 
			String keyStr,String beginDate,String endDate,String sqlName,String failureMessage,Integer fllowType) throws Exception;
	
	public Integer getCountByStateAndEId(List<Integer> states,Integer[]eIds,String keys) throws Exception;
	public Integer getCountByStateAndEId(List<Integer> states,Integer[]eIds,String keys,
			String beginDate,String endDate,String sqlName,String failureMessage,Integer fllowType) throws Exception;

	public List<Map<String, Object>> searchList(Integer pageIndex,
			Integer pageSize,Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanIds, String beginDate, String endDate, String orderKey,
			String[] follow,String[] wage,String[] unit,Integer[] vIds) throws Exception;

	public Integer searchCount(Integer[] dids, Integer[] eids, Integer[] statesIds,
			Integer[] sIds, String[] customerL, String[] loanTypes,
			String beginDate, String endDate,
			String[] follow,String[] wage,String[] unit,Integer[] vIds) throws Exception;
}
