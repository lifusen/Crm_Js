package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.db.BaseDao;

public interface AllotCustomerDao extends BaseDao<AllotCustomer>{

	void updateStaff(Integer cId, Integer eId)throws Exception;

	List<AllotCustomer> getListByCId(Integer[] ids)throws Exception;

	void batchUpdateStaff(Integer[] idArrayInt, Integer eId)throws Exception;

	List<Map<String, Object>> getAllotToDepartList(Integer pageIndex, Integer pageSize,
			List<Integer> allottodepartments, Integer id,String keyStr) throws Exception;

	Integer getAllotToDepartCount(List<Integer> allottodepartments, Integer id,String keyStr) throws Exception;

	List<Map<String, Object>> getAllotToEmplList(Integer pageIndex,
			Integer pageSize, List<Integer> allottoemployees, Integer dId,String keyStr,Integer companyId,Integer departmentId) throws Exception;

	Integer getAllotToEmplCount(List<Integer> allottoemployees, Integer dId,String keyStr,Integer companyId,Integer departmentId) throws Exception;

	List<Map<String, Object>> getAssignedOrderList(Integer pageIndex,
			Integer pageSize, Integer id, String keyStr) throws Exception;

	Integer getAssignedCount(Integer id, String keyStr) throws Exception
	;

	List<Map<String, Object>> getReceiveOrderList(Integer pageIndex,
			Integer pageSize, Integer[] ids, String keyStr) throws Exception;

	Integer getReceiveOrderCount(Integer[] ids, String keyStr) throws Exception;
}
