package cn.itproject.crm.service;

import java.util.Date;
import java.util.List;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Customer.CustomerAddType;
import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.controller.viewbean.ShowCustomer;

/**
 * 客户业务接口
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月14日
 *
 *       version 1.0
 */
public interface CustomerService extends BaseService<Customer> {

	/**
	 * 根据客户状态获取客户列表
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param state
	 *            状态
	 * @param eId
	 *            员工id
	 * @return
	 * @throws Exception
	 */
	List<Customer> getListByState(Integer pageIndex, Integer pageSize, Integer state, Integer eId) throws Exception;

	/**
	 * 修改客户状态
	 * 
	 * @param cId
	 *            客户id
	 * @param state
	 *            状态
	 * @throws Exception
	 */
	void updateState(Integer cId, Integer state) throws Exception;

	/**
	 * 修改客户可见性
	 * 
	 * @param cId
	 *            客户id
	 * @param visibility
	 *            可见性（自留）
	 * @throws Exception
	 */
	void updateVisibility(Integer cId, Integer visibility) throws Exception;

	/**
	 * 获取应该分配到员工的客户列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @param dId
	 *            部门ID
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @return
	 * @throws Exception
	 */
	List<Customer> getAllotToEmployeeList(Integer roleId, Integer dId, Integer pageIndex, Integer pageSize)
			throws Exception;

	/**
	 * 获取新订单
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param id
	 *            员工ID列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	List<ShowCustomer> getNewList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr) throws Exception;

	/**
	 * 根据状态获取客户数量
	 * 
	 * @param state
	 *            状态
	 * @param eId
	 *            员工id
	 * @return
	 * @throws Exception
	 */
	Integer getCountByState(Integer state, Integer eId) throws Exception;

	/**
	 * 获取该分配到员工的客户数量
	 * 
	 * @param roleId
	 *            角色id
	 * @param dId
	 *            部门id
	 * @return
	 * @throws Exception
	 */
	Integer getAllotToEmployeeCount(Integer roleId, Integer dId) throws Exception;

	/**
	 * 获取新订单数量
	 * 
	 * @param id
	 *            员工id列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	Integer getNewOrderCount(Integer[] id, String keyStr) throws Exception;

	/**
	 * 已分配订单
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param id
	 *            员工id列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	List<ShowCustomer> getAssignedOrderList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception;

	/**
	 * 获取已分配订单的数量
	 * 
	 * @param id
	 *            员工id列表
	 * @param keyStr搜索条件
	 * @return
	 * @throws Exception
	 */
	Integer getAssignedOrderCount(Integer[] id, String keyStr) throws Exception;

	/**
	 * 获取已经领单列表
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param id
	 *            员工id列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	List<ShowCustomer> getReceiveOrderList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception;

	/**
	 * 获取已经领单的数量
	 * 
	 * @param id
	 *            员工id列表
	 * @param keyStr搜索条件
	 * @return
	 * @throws Exception
	 */
	Integer getReceiveOrderCount(Integer[] id, String keyStr) throws Exception;

	/**
	 * 获取已经领单列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param departmentId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	List<ShowCustomer> getReceiveOrderList(Integer pageIndex, Integer pageSize, Integer departmentId,
			Integer employeeId) throws Exception;

	/**
	 * 获取已经领单的数量
	 * 
	 * @param departmentId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	Integer getReceiveOrderCount(Integer departmentId, Integer employeeId) throws Exception;

	/**
	 * 批量添加导入的客户
	 * 
	 * @param customers
	 *            客户集合
	 * @param customerRoster
	 *            客户名单
	 * @param employee
	 *            操作人
	 * @throws Exception
	 */
	void batchAddCustomer(List<Customer> customers, CustomerRoster customerRoster, Employee employee) throws Exception;

	/**
	 * 获取有效客户列表
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param id
	 *            员工id列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	List<ShowCustomer> getValidCustomerList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception;

	/**
	 * 获取有效客户列表数量
	 * 
	 * @param id
	 *            员工id列表
	 * @param keyStr搜索条件
	 * @return
	 * @throws Exception
	 */
	Integer getValidCustomerCount(Integer[] id, String keyStr) throws Exception;
	// List<Customer> getCustomerList(Integer pageIndex,Integer pageSize) throws
	// Exception;

	/**
	 * 通过添加类型获取员工数量
	 * 
	 * @param addtype
	 *            添加类型（市场部添加、业务员添加）
	 * @param id
	 *            客户名单id(导入)
	 * @return
	 * @throws Exception
	 */
	Integer getcountByAddType(CustomerAddType addtype, int id) throws Exception;

	/**
	 * 根据导入的客户名单获取客户列表
	 * 
	 * @param rId
	 *            名单id
	 * @param count
	 *            查询数量
	 * @return
	 * @throws Exception
	 */
	List<Customer> getListByAddType(Integer rId, Integer count) throws Exception;

	/**
	 * 业务员添加客户
	 * 
	 * @param customer
	 * @param loginEmployee
	 */
	void addSalesmanCustomer(Customer customer, Employee employee) throws Exception;

	/**
	 * 申请客户无效
	 * 
	 * @param cId
	 *            客户id
	 * @throws Exception
	 */
	void applyNullity(Integer cId) throws Exception;

	/**
	 * 获取申请无效的客户列表
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param ids
	 *            员工id
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	List<ShowCustomer> getApplyNullityList(Integer pageIndex, Integer pageSize, Integer[] ids, String keyStr)
			throws Exception;

	/**
	 * 获取申请无效客户数量
	 * 
	 * @param ids
	 *            员工id列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	Integer getApplyNullityCount(Integer[] ids, String keyStr) throws Exception;

	/**
	 * 释放公共池
	 * 
	 * @param cId
	 *            客户id
	 * @throws Exception
	 */
	void releasePublic(Integer cId) throws Exception;

	/**
	 * 判断该客户是否已跟进
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	Boolean isFollow(Integer customerId) throws Exception;

	/**
	 * 修改客户跟进状态(当第一次客户跟进时,才修改)
	 * 
	 * @param customerId
	 * @throws Exception
	 */
	@Deprecated
	void updateFollowState(Integer customerId) throws Exception;

	/**
	 * 审核无效客户
	 * 
	 * @param cIds
	 *            客户id列表
	 * @param state
	 *            状态
	 * @throws Exception
	 */
	void verify(Integer[] cIds, Integer state) throws Exception;

	/**
	 * 获取无效客户列表
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param ids
	 *            员工id列表
	 * @param keyStr
	 *            搜索条件
	 * @return
	 * @throws Exception
	 */
	List<ShowCustomer> getNullityList(Integer pageIndex, Integer pageSize, Integer[] ids, String keyStr)
			throws Exception;

	/**
	 * 获取无效客户数量
	 * 
	 * @param ids
	 *            员工id列表
	 * @param keyStr搜索条件
	 * @return
	 * @throws Exception
	 */
	Integer getNullityCount(Integer[] ids, String keyStr) throws Exception;

	/**
	 * 删除客户
	 * 
	 * @param id
	 *            客户id
	 * @param hId
	 *            客户历史id
	 * @throws Exception
	 */
	void deleteCustomer(Integer id) throws Exception;

	void updateCustomer(Customer updateCustomer) throws Exception;

	// List<CollateralViewBean> buildCollateralViewBean(Customer customer);

	/**
	 * 
	 * @param id
	 *            客户ID
	 * @return 客户对象
	 */
	Customer getBasicCustomer(Integer id) throws Exception;

	/**
	 * 获取跟进时间>30天的客户
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Integer> getListByFllowDate() throws Exception;

	/**
	 * 批量释放
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void releaseCommonPool(List<Integer> ids) throws Exception;

	/**
	 * 构建抵押物(客户资产)
	 * 
	 * @param customer
	 * @return
	 */
	public List<CollateralViewBean> buildCollateralViewBean(Customer customer) throws Exception;

	/**
	 * 公共池领单
	 * 
	 * @param id
	 * @param employee
	 * @throws Exception
	 */
	void receive(Integer id, Employee employee) throws Exception;

	/**
	 * 获取客户的状态
	 * 
	 * @param customerId
	 *            客户状态
	 * @return
	 * @throws Exception
	 */
	Integer getCustomerState(Integer customerId) throws Exception;

	Integer getCountByEId(Integer id) throws Exception;

	Integer getCustomerByPhoneAndState(String phone, List<Integer> validlist) throws Exception;

	/**
	 * 添加Excel客户
	 * 
	 * @param customer
	 *            客户对象
	 * @param customerRoster
	 *            客户名单
	 * @param employee
	 *            员工
	 * @param customerSource
	 *            客户来源
	 * @param operationType
	 *            操作类型,1:批量导入分配给业务部门/2:批量导入客户到公共池
	 * @throws Exception
	 */
	public void addExcelCustomer(Customer customer, CustomerRoster customerRoster, Employee employee,
			CustomerSource customerSource, Integer operationType) throws Exception;

	/**
	 * 修改客户的等级
	 * 
	 * @param id
	 *            客户ID
	 * @param level
	 *            客户等级名称
	 * @throws Exception
	 */
	public void updateCustomerLevel(Integer id, String level) throws Exception;

	/**
	 * 修改客户的关注等级
	 * 
	 * @param id
	 *            客户ID
	 * @param level
	 *            客户的关注等级名称
	 * @throws Exception
	 */
	public void updateAttentionLevel(Integer id, String level) throws Exception;

	Integer getReleaseById(Integer id, Date date) throws Exception;

	Integer getReceiveById(Integer id, Date date) throws Exception;

	void updateLastTime(Integer id) throws Exception;

	/**
	 * 获取指定员工持有有效客户数量:c.state in (2,3);
	 * 
	 * @param eId
	 * @return
	 * @throws Exception
	 *             2017年1月22日 下午3:39:36 by SwordLiu
	 */
	Integer getValidCustomerCount(Integer eId) throws Exception;
}
