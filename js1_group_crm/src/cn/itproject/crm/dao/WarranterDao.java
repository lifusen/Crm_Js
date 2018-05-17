package cn.itproject.crm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.db.BaseDao;

public interface WarranterDao extends BaseDao<Customer>{

	List<Map<String, Object>> orderList(WarrantOrderQuery query) throws Exception;

	Integer getOrderCount(WarrantOrderQuery query)throws Exception;

	List<Map<String, Object>> search(Integer[] companyIdArray, Integer[] depIdArray, Integer[] empIdArray,
			Integer[] typeArray, String[] loanTypeArray, Date signDateStart, Date signDateEnd,Integer pageIndex,Integer pageSize) throws Exception;

	Integer searchCount(Integer[] companyIdArray, Integer[] depIdArray, Integer[] empIdArray, Integer[] typeArray,
			String[] loanTypeArray, Date signDateStart, Date signDateEnd) throws Exception;

}