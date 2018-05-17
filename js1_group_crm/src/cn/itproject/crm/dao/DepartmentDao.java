package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.db.BaseDao;

public interface DepartmentDao extends BaseDao<Department>{
	List<Department> queryListByName(String type,Integer companyId) throws Exception;

	List<Department> getByCompanyId(Integer companyId) throws Exception;
	
	List<Department> getDepartmentListByCompanyId(Integer companyId) throws Exception;
	
	List<Department> getDepartmentList() throws Exception;

	/**
	 * 获取所有部门的ID和Name
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllDepartmentIdAndName(Integer companyId,Integer type) throws Exception;

	List<Department> getByCompanyIds(Integer[] idArray,String type) throws Exception;

	List<Department> getDeparmentByCompanyType(Integer companyType) throws Exception;

}
