package cn.itproject.crm.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.CDEVo;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceSupport<Employee> implements EmployeeService{

	@Resource
	private EmployeeDao employeeDao;
	
	@Resource
	private DepartmentService departmentService; 
	
	@Override
	protected BaseDao<Employee> getBaseDao() {
		return employeeDao;
	}
	@Override
	@Cacheable("employeeCache")
	public Employee getEmployeeByAccount(String account) throws Exception {
		return employeeDao.get("account = :account", account);
	}
	@Override
	public List<Employee> getList(Integer pageIndex, Integer pageSize)
			throws Exception {
		return employeeDao.getList(pageIndex,pageSize,LoginUserUtil.getCompanyId());
	}
	@Override
	public Employee getEmployee(String account, String password) throws Exception{
		return employeeDao.get("account = :account and password = :password", account,password);
	}
	@Override
	@Cacheable("employeeCache")
	public List<Employee> getEmployeeByDId(Integer eId, Integer dId)
			throws Exception {
		return employeeDao.getListByDId(eId,dId);
	}
	@Override
	@Cacheable("employeeCache")
	public List<Employee> getEmployeeByDIds(Integer[] ids) throws Exception {
		return employeeDao.getListByDIds(ids);
	}
	@Override
	public Integer getValidCount() throws Exception {
		return employeeDao.getValidCount(LoginUserUtil.getCompanyId());
	}
	@Override
	public List<Integer> getEmployeeIds() throws Exception {
		return employeeDao.getEmployeeIds(LoginUserUtil.getCompanyId());
	}
	@Override
	public List<Integer> getEmployeeIdsByDId(Integer departmentId)
			throws Exception {
		return employeeDao.getEmployeeIdsByDId(departmentId);
	}
	@Override
	@Cacheable("employeeCache")
	public List<Employee> getAllNegotiators() throws Exception {
		String hql = "select e from Employee e where e.talks = '是' and status = 1";
		return employeeDao.getList(hql);
	}
	@Override
	public List<Employee> getAllSalesman() throws Exception {
		List<Department> departments = departmentService.getDeparmentByName("业务");
		List<Employee> employees = new ArrayList<Employee>();
		if (departments!=null) {
			Integer[] ids = new Integer[departments.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = departments.get(i).getId();
			}
			employees= getEmployeeByDIds(ids);
		}
		return employees;
	}
	@Override
	public boolean checkRole(Integer roleId, Integer departmentId) throws Exception{
		int count = employeeDao.getCountByRoleAnd(roleId,departmentId);
		if (count==0) {
			return true;
		}
		return false;
	}
	@Override
	@Cacheable("employeeCache")
	public Employee getEmployee(Integer id) throws Exception {
		return employeeDao.get(id);
	}
	@Override
	@CacheEvict(value="employeeCache",allEntries=true)
	public void addEmployee(Employee employee) throws Exception {
		employeeDao.save(employee);
	}
	@Override
	@CacheEvict(value="employeeCache",allEntries=true)
	public void updateEmployee(Employee employee) throws Exception {
		employeeDao.update(employee);
	}
	@Cacheable("employeeCache")
	@Override
	public List<Employee> getAllEmployee() throws Exception {
		return employeeDao.queryList();
	}
	@Cacheable("employeeCache")
	@Override
	public Map<Integer, String> getAllEmployeeMap() throws Exception {
		List<Map<String, Object>> list = employeeDao.getAllEmployeeIdAndName(null);
		return handleListToMapOfIdName(list);
	}
	@Override
	@Cacheable("employeeCache")
	public List<Map<String, Object>> getEmployeeListMapByDepartmentId(Integer departmentId) throws Exception {
		return employeeDao.getAllEmployeeIdAndName(departmentId);
	}

	
	@Cacheable("redisCache-3m") // 缓存3分钟
	@Override
	public Map<Integer, CDEVo> getCDEMapOfCompanyId() throws Exception {
		// 获取公司负责人信息,以公司ID作为key
		List<CDEVo> list = employeeDao.getCDEList(1);
		Map<Integer, CDEVo> cdeMap = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (CDEVo cdeVo : list) {
				cdeMap.put(cdeVo.getCompanyId(), cdeVo);
			}
		}
		return cdeMap;
	}
	
	@Cacheable("redisCache-3m") // 缓存3分钟
	@Override
	public Map<Integer, CDEVo> getCDEMapOfDepartmentId() throws Exception {
		// 获取公司部门员工信息,以部门ID作为key
		List<CDEVo> list = employeeDao.getCDEList(Constant.departmentManagerRoles);
		Map<Integer, CDEVo> cdeMap = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (CDEVo cdeVo : list) {
				cdeMap.put(cdeVo.getDepartmentId(), cdeVo);
			}
		}
		return cdeMap;
	}
	
	@Cacheable("redisCache-3m") // 缓存3分钟
	@Override
	public Map<Integer, CDEVo> getCDEMapOfEmployeeId() throws Exception {
		// 获取公司部门员工信息,以员工ID作为key
		List<CDEVo> list = employeeDao.getCDEList();
		Map<Integer, CDEVo> cdeMap = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (CDEVo cdeVo : list) {
				cdeMap.put(cdeVo.getEmployeeId(), cdeVo);
			}
		}
		return cdeMap;
	}
	
	@Override
	public void updateEmail(Integer employeeId,String email) throws Exception{
		employeeDao.updateEmail(employeeId, email);
	}
	
	@Override
	public String getEmail(Integer employeeId) throws Exception {
		return employeeDao.getEmail(employeeId);
	}
	
	@Override
	public boolean emailExists(String email) throws Exception {
		Long count = employeeDao.getEmailCount(email);
		return count > 0;
	}
	
	@Override
	public String getAccountByEmail(String email) throws Exception {
		return employeeDao.getAccountByEmail(email);
	}
	
	@Override
	public void updatePasswordByEmail(String email,String password) throws Exception {
		employeeDao.updatePasswordByEmail(email,password);
	}
	
	@Override
	public List<Map<String, Object>> getAccountListByEmail(String email) throws Exception {
		return employeeDao.getAccountListByEmail(email);
	}
	
	@Override
	public void updatePasswordByEmployeeId(Integer employeeId, String password) throws Exception {
		employeeDao.updatePasswordByEmployeeId(employeeId, password);
	}
	@Override
	public List<Employee> getEmployeeByCompanyId(Integer companyId) throws Exception {
		return employeeDao.getEmployyeByCompanyId(companyId);
	}
	@Override
	public List<Map<String, Object>> getCustomerNO(Integer employeeId) throws Exception {
		return employeeDao.getCustomerNO(employeeId);
	}
	@Override
	public void saveCustomerNO(Integer eId, Integer currentNO) throws Exception {
		employeeDao.saveCustomerNO(eId, currentNO);
	}
}
