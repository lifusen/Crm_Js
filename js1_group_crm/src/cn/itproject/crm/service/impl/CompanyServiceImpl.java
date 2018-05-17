package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.dao.CompanyDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceSupport<Company> implements CompanyService{

	@Override
	protected BaseDao<Company> getBaseDao() {
		return companyDao;
	}
	@Resource
	private CompanyDao companyDao;
	
	
	@Override
	@Cacheable("companyCache")
	public List<Company> getCompanyList(Integer pageIndex, Integer pageSize) throws Exception{
		return companyDao.getList(pageIndex,pageSize);
	}

	@Override
	@Cacheable("companyCache")
	public Integer getCompanyCount() throws Exception{
		return companyDao.getCount();
	}

	@Override
	@Cacheable("companyCache")
	public List<Company> getAllCompany(Integer type) throws Exception {
		if (type==null) {
			return companyDao.queryList();
		}
		return companyDao.queryList(" type=:type", null, type);
	}
	
	@Override
	@Cacheable("companyCache")
	public Map<Integer, String> getAllCompanyMap() throws Exception {
		List<Map<String, Object>> list = companyDao.getAllCompanyIdAndName();
		return handleListToMapOfIdName(list);
	}

	@Override
	@Cacheable("companyCache")
	public Company getCompany(Integer id) throws Exception {
		return companyDao.get(id);
	}

	@Override
	@CacheEvict(value="companyCache",allEntries=true)
	public void addCompany(Company company) throws Exception {
		companyDao.save(company);
	}

	@Override
	@CacheEvict(value="companyCache",allEntries=true)
	public void updateCompany(Company company) throws Exception {
		companyDao.update(company);
	}

	@Override
	@CacheEvict(value="companyCache",allEntries=true)
	public void deleteCompany(Integer id) throws Exception {
		companyDao.remove(id);
	}

	@Override
	@Cacheable("companyCache")
	public List<Company> getCompanyByType(int type) throws Exception{
		return companyDao.getList("from Company where type = ?", type);
	}
}
