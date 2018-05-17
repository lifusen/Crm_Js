package cn.itproject.crm.service;


import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Employee;

public interface AllotCustomerService extends BaseService<AllotCustomer>{

	/**
	 * 修改当前所属员工
	 * @param cId	客户id
	 * @param eId	员工id
	 * @throws Exception
	 */
	void updateByCId(Integer cId, Integer eId) throws Exception;

	/**
	 * 分配到部门
	 * @param allotCustomer	 分配客户
	 * @throws Exception
	 */
	void allotToDepartment(AllotCustomer allotCustomer) throws Exception;

	/**
	 * 分配到员工
	 * @param cId	客户id
	 * @param eId	员工id
	 * @param nowEmployee	当前员工
	 * @throws Exception
	 */
	void allotToEmployee(Integer cId, Integer eId,Employee nowEmployee)throws Exception;

	/**
	 * 批量分配到部门
	 * @param dId	部门id
	 * @param idArrayInt	id数组
	 * @param nowEmployee	当前员工
	 * @throws Exception
	 */
	void batchAllotToDepartment(Integer companyId,Integer dId, Integer[] idArrayInt,Employee nowEmployee) throws Exception;

	/**
	 * 批量分配到员工
	 * @param eId	员工id
	 * @param idArrayInt	id数组
	 * @param nowEmployee	当前员工
	 * @throws Exception
	 */
	void batchAllotToEmployee(Integer eId, Integer[] idArrayInt,
			Employee nowEmployee) throws Exception;

	/**
	 * 批量分配导入员工到部门	
	 * @param dId	部门id
	 * @param customers	客户集合
	 * @param nowEmployee	当前员工
	 * @param rId			名单id
	 * @throws Exception
	 */
	void batchAllotToDepartment(Integer companyId,Integer dId, List<Customer> customers,
			Employee nowEmployee,Integer rId) throws Exception;

	/**
	 * 获取分配到部门的列表
	 * @param pageIndex
	 * @param pageSize
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAllotToDepartmentList(Integer pageIndex,
			Integer pageSize,Integer id,String keyStr)throws Exception;

	/**
	 * 获取分配到部门的数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer getAllotToDepartmentCount(Integer id,String keyStr)throws Exception;

	/**
	 * 获取分配到员工的列表
	 * @param dId	部门id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllotToEmployeeList(Integer dId,
			Integer pageIndex, Integer pageSize,String keyStr,Integer companyId,Integer departmentId) throws Exception;

	Integer getAllotToEmployeeCount(Integer dId,String keyStr,Integer companyId,Integer departmentId) throws Exception;

	List<Map<String, Object>> getAssignedOrderList(Integer pageIndex,
			Integer pageSize, Integer id, String keyStr) throws Exception;

	Integer getAssignedCount(Integer id, String keyStr)throws Exception;

	List<Map<String, Object>> getReceiveOrderList(Integer pageIndex,
			Integer pageSize, Integer[] ids, String keyStr) throws Exception;

	Integer getReceiveOrderCount(Integer[] ids, String keyStr) throws Exception;
	
	/**
	 * 判断客户是否超过系统设置上限，返回超过客户数
	 * @param employeeId 员工ID
	 * @param customerCount 分配给员工的客户数量
	 * @return count 超过系统限制客户数量上限的数量，如果返回值大于0，则超过限制
	 * @throws Exception
	 * 2017年1月23日 上午10:24:10 by SwordLiu
	 */
	Integer getCustomerIsOverrun(Integer employeeId, Integer customerCount) throws Exception;

}
