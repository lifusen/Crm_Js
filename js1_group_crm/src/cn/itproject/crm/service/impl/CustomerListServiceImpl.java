package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;



















import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.dao.CustomerListDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.CustomerListService;

@Service("customerListService")
public class CustomerListServiceImpl extends BaseServiceSupport<Customer> implements CustomerListService{

	@Resource
	private CustomerListDao customerListDao;
	
	@Override
	protected BaseDao<Customer> getBaseDao() {
		return customerListDao;
	}
	
	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex,
			Integer pageSize, Integer[] eIds,List<Integer> states, String keyStr) throws Exception {
		return customerListDao.getCustomersByStateAndEId(pageIndex, pageSize, eIds, states, keyStr,null,null);
	}

	@Override
	public Integer getCountByStateAndEId(List<Integer> states, Integer[] eIds,String keys)
			throws Exception {
		return customerListDao.getCountByteStateAndEId(states, eIds,keys,null,null);
	}

	@Override
	public List<Map<String, Object>> searchList(Integer pageIndex,
			Integer pageSize,Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanIds, String beginDate, String endDate, String orderKey
			,String[] follow,String[] wage,String[] unit,Integer[] vIds)
			throws Exception {
		return customerListDao.srarchList("searchCustomerList",pageIndex,
				pageSize,dids, eids,
				statesIds, sIds, customerL,
				loanIds, beginDate, endDate, orderKey
				, follow, wage, unit, vIds);
	}

	@Override
	public Integer searchCount(
			Integer[] dids, Integer[] eids, Integer[] statesIds,
			Integer[] sIds, String[] customerL, String[] loanTypes,
			String beginDate, String endDate,String[] follow,String[] wage,String[] unit,Integer[] vIds) throws Exception {
		return customerListDao.searchCount("searchCountByState",dids, eids, statesIds,
				sIds, customerL, loanTypes,
				beginDate, endDate
				, follow, wage, unit, vIds);
	}

	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(
			Integer pageIndex, Integer pageSize, Integer[] eIds,
			List<Integer> states, String keyStr,String beginDate,String endDate, String sqlName,String failureMessage,Integer fllowType)
			throws Exception {
		return customerListDao.getCustomersByStateAndEId(pageIndex, pageSize, eIds, states, keyStr,
				beginDate,endDate, sqlName,failureMessage,fllowType,null,null);
	}

	@Override
	public Integer getCountByStateAndEId(List<Integer> states, Integer[] eIds,
			String keys,String beginDate,String endDate, String sqlName,String failureMessage,Integer fllowType) throws Exception {
		return customerListDao.getCountByteStateAndEId(states, eIds, keys,beginDate,endDate, 
				sqlName,failureMessage,fllowType,null,null);
	}
}
