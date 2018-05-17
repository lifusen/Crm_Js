package cn.itproject.crm.dao;


import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.CDEVo;
import cn.itproject.crm.db.BaseDao;

public interface EmployeeDao extends BaseDao<Employee>{

	List<Employee> getList(Integer pageIndex, Integer pageSize,Integer companyId) throws Exception;

	List<Employee> getListByDId(Integer eId, Integer dId) throws Exception;
	
	List<Employee> getListByDIds(Integer[] ids) throws Exception;

	Integer getValidCount(Integer companyId) throws Exception;

	/**
	 * 根据客户ID获取员工信息
	 * @param customerIds
	 * @return
	 */
	Employee getEmployeeInfoByCustomerId(Integer customerId) throws Exception;
	
	/**
	 * 获取所有员工的ID
	 * @return
	 */
	List<Integer> getEmployeeIds(Integer companyId) throws Exception;
	
	/**
	 * 根据部门ID获取其所有员工的ID
	 * @param departmentId 部门ID
	 * @return 该部门下的所有员工ID
	 */
	List<Integer> getEmployeeIdsByDId(Integer departmentId) throws Exception;

	int getCountByRoleAnd(Integer roleId, Integer departmentId)throws Exception;

	/**
	 * 获取所有员工的id和name集合
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllEmployeeIdAndName(Integer departmentId) throws Exception;
	
	/**
	 * 获取公司部门员工信息
	 * @param roleIds
	 * @return
	 * @throws Exception
	 */
	List<CDEVo> getCDEList(Integer... roleIds) throws Exception;
	
	/**
	 * 修改Email
	 * @param employeeId
	 * @param email
	 * @throws Exception
	 */
	void updateEmail(Integer employeeId,String email) throws Exception;

	String getEmail(Integer employeeId) throws Exception;

	/**
	 * 获取邮箱个数
	 * @param email
	 * @return
	 * @throws Exception
	 */
	Long getEmailCount(String email) throws Exception;
	
	String getAccountByEmail(String email) throws Exception;

	void updatePasswordByEmail(String email,String password) throws Exception;

	List<Map<String, Object>> getAccountListByEmail(String email) throws Exception;

	void updatePasswordByEmployeeId(Integer employeeId, String password) throws Exception;

	List<Employee> getEmployyeByCompanyId(Integer companyId) throws Exception;

	List<Map<String, Object>> getCustomerNO(Integer employeeId) throws Exception;

	void saveCustomerNO(Integer eId, Integer currentNO) throws Exception;
}
