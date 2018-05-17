package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.CDEVo;

public interface EmployeeService extends BaseService<Employee>{

	/**
	 * 登录账号获取员工
	 * @param account	员工账号
	 * @return
	 * @throws Exception
	 */
	Employee getEmployeeByAccount(String account) throws Exception;

	/**
	 * 获取员工列表
	 * @param pageIndex	当前页
	 * @param pageSize	每页显示的数据行数
	 * @return
	 * @throws Exception
	 */
	List<Employee> getList(Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 通过员工账号和密码获取员工
	 * @param account	账号
	 * @param password	密码
	 * @return
	 * @throws Exception
	 */
	Employee getEmployee(String account, String password) throws Exception;

	/**
	 * 根据部门经理Id获取该部门下的员工
	 * @param eId	部门经理的Id
	 * @param dId	部门ID
	 * @return
	 * @throws Exception
	 */
	List<Employee> getEmployeeByDId(Integer eId, Integer dId) throws Exception;

	/**
	 * 根据部门ID列表获取所有员工
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	List<Employee> getEmployeeByDIds(Integer[] ids) throws Exception;

	/**
	 * 获取员工（未删除的）
	 * @return
	 * @throws Exception
	 */
	Integer getValidCount() throws Exception;

	/**
	 * 获取所有员工的ID
	 * @return
	 * @throws Exception
	 */
	List<Integer> getEmployeeIds() throws Exception;
	
	/**
	 * 根据部门ID获取所有员工ID
	 * @param departmentId 部门ID
	 * @return 该部门下的所有员工ID
	 */
	List<Integer> getEmployeeIdsByDId(Integer departmentId) throws Exception;

	/**
	 * 获取到所有的谈判师
	 * @return
	 * @throws Exception
	 */
	List<Employee> getAllNegotiators() throws Exception;
	
	/**
	 * 获取所有的业务员
	 * @return
	 * @throws Exception
	 */
	List<Employee> getAllSalesman() throws Exception;

	/**
	 * 检查当前员工角色是否合法
	 * @param roleId
	 * @param departmentId
	 * @return
	 */
	boolean checkRole(Integer roleId, Integer departmentId)throws Exception;
	
	Employee getEmployee(Integer id) throws Exception;
	
	void addEmployee(Employee employee) throws Exception;
	
	void updateEmployee(Employee employee) throws Exception;
	
	/**
	 * 获取所有的员工信息,用于前台页面调用
	 * @return
	 * @throws Exception
	 */
	List<Employee> getAllEmployee() throws Exception;

	/**
	 * 获取所有员工的Map信息
	 * @return
	 * @throws Exception
	 */
	Map<Integer, String> getAllEmployeeMap() throws Exception;
	
	/**
	 * 根据部门ID获取员工id/name集合
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getEmployeeListMapByDepartmentId(Integer departmentId) throws Exception;
	
	
	
	/**
	 * 获取公司部门员工信息,用公司ID作为key
	 * 通过公司ID获取负责人信息
	 * @return
	 * @throws Exception
	 */
	Map<Integer, CDEVo> getCDEMapOfCompanyId() throws Exception;
	
	/**
	 * 获取公司部门员工信息,用部门ID作为key
	 * 通过部门ID获取负责人信息
	 * @return
	 * @throws Exception
	 */
	Map<Integer, CDEVo> getCDEMapOfDepartmentId() throws Exception;
	
	/**
	 * 获取公司部门员工信息,用员工ID作为key
	 * @return
	 * @throws Exception
	 */
	Map<Integer, CDEVo> getCDEMapOfEmployeeId() throws Exception;
	
	/**
	 * 修改Email
	 * @param employeeId
	 * @param email
	 * @throws Exception
	 */
	void updateEmail(Integer employeeId,String email) throws Exception;

	String getEmail(Integer employeeId) throws Exception;
	
	/**
	 * 判断邮箱是否存在
	 * @param email
	 * @return
	 * @throws Exception
	 */
	boolean emailExists(String email) throws Exception;
	
	String getAccountByEmail(String email) throws Exception;
	
	/**
	 * 通过邮箱修改密码
	 * @param email
	 * @throws Exception
	 */
	void updatePasswordByEmail(String email,String password) throws Exception;
	
	/**
	 * 根据Email获取账号列表
	 * @param email
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAccountListByEmail(String email) throws Exception;

	void updatePasswordByEmployeeId(Integer employeeId, String password) throws Exception;

	List<Employee> getEmployeeByCompanyId(Integer companyId) throws Exception;

	/**
	 * 获取指定员工持有客户数量上限
	 * @param employeeId
	 * @return
	 * @throws Exception
	 * 2017年1月20日 上午11:58:13 by SwordLiu
	 */
	List<Map<String, Object>> getCustomerNO(Integer employeeId) throws Exception;

	/**
	 * 修改指定客户持有客户数量上限
	 * @param eId
	 * @param currentNO
	 * @return
	 * @throws Exception
	 * 2017年1月20日 下午2:31:33 by SwordLiu
	 */
	void saveCustomerNO(Integer eId, Integer currentNO) throws Exception;
}
