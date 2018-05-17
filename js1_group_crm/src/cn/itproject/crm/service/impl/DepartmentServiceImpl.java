package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.input.DemuxInputStream;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.dao.DepartmentDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceSupport<Department> implements DepartmentService{
	
	@Resource
	private DepartmentDao departmentDao;
	@Override
	protected BaseDao<Department> getBaseDao() {
		return departmentDao;
	}
	@Override
	public List<Department> getDeparmentByName(String name)throws Exception{
		
		return departmentDao.queryListByName(name,LoginUserUtil.getCompanyId());
	}
	@Override
	@Cacheable("departmentCache")
	public List<Department> getByCompanyId(Integer companyId) throws Exception {
		return departmentDao.getByCompanyId(companyId);
	}
	@Override
	public List<Department> getAll() throws Exception{
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
//			return departmentDao.getList(" from Department where companyId = ?", LoginUserUtil.getCompanyId());
			return departmentDao.getDepartmentListByCompanyId(LoginUserUtil.getCompanyId());
		}else {
//			return departmentDao.queryList();
			return departmentDao.getDepartmentList();
		}
	}
	@Override
	@Cacheable("departmentCache")
	public Department getDepartment(Integer id) throws Exception {
		return departmentDao.get(id);
	}
	@Override
	@CacheEvict(value="departmentCache",allEntries=true)
	public void addDepartment(Department department) throws Exception {
		departmentDao.save(department);
	}
	@Override
	@CacheEvict(value="departmentCache",allEntries=true)
	public void updateDepartment(Department department) throws Exception {
		departmentDao.update(department);
	}
	@Override
	@CacheEvict(value="departmentCache",allEntries=true)
	public void deleteDepartment(Integer id) throws Exception {
		departmentDao.remove(id);
	}
	@Override
	@Cacheable("departmentCache")
	public List<Department> getAllDepartment() throws Exception {
		return departmentDao.queryList();
	}
	
	@Override
	@Cacheable("departmentCache")
	public Map<Integer, String> getAllDepartmentMap() throws Exception {
		List<Map<String, Object>> list = departmentDao.getAllDepartmentIdAndName(null,null);
		return handleListToMapOfIdName(list);
	}
	
	@Override
	@Cacheable("departmentCache")
	public List<Map<String, Object>> getDepartmentListMapByCompanyId(Integer companyId,Integer type) throws Exception {
		return departmentDao.getAllDepartmentIdAndName(companyId,type);
	}
	
	@Override
	public List<Department> getDepartmentByCompanyIds(Integer[] idArray,String type) throws Exception {
		return departmentDao.getByCompanyIds(idArray,type);
	}
	@Override
	public List<Department> getDepartmentByCompanyId(Integer companyId) throws Exception {
		return departmentDao.getByCompanyId(companyId);
	}
	@Override
	public List<Department> getDeparmentByCompanyType(Integer companyType) throws Exception {
		return departmentDao.getDeparmentByCompanyType(companyType);
	}
	
	
}
