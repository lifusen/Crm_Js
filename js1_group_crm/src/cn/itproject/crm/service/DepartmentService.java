package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Department;

public interface DepartmentService extends BaseService<Department>{

	List<Department> getDeparmentByName(String string)throws Exception;

	List<Department> getByCompanyId(Integer companyId)throws Exception;

	List<Department> getAll() throws Exception;
	
	Department getDepartment(Integer id) throws Exception;
	
	void addDepartment(Department department) throws Exception;
	
	void updateDepartment(Department department) throws Exception;
	
	void deleteDepartment(Integer id) throws Exception;
	
	/**
	 * 获取所有的部门
	 * @return
	 * @throws Exception
	 */
	List<Department> getAllDepartment() throws Exception;
	
	/**
	 * 获取所有部门的Map key:部门ID,value:部门名称
	 * @return
	 * @throws Exception
	 */
	Map<Integer, String> getAllDepartmentMap() throws Exception;
	
	/**
	 * 通过公司ID获取部门集合
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getDepartmentListMapByCompanyId(Integer companyId,Integer type) throws Exception;

	List<Department> getDepartmentByCompanyIds(Integer[] idArray,String type) throws Exception;

	List<Department> getDepartmentByCompanyId(Integer companyId) throws Exception;

	/**
	 * 查找指定公司类型下面的所有部门
	 * @param companyType
	 * @return
	 * @throws Exception
	 * 2017年1月14日 上午11:37:19 by SwordLiu
	 */
	List<Department> getDeparmentByCompanyType(Integer companyType) throws Exception;

}
