package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.db.BaseDao;

public interface CompanyDao extends BaseDao<Company>{

	List<Company> getList(Integer pageIndex, Integer pageSize) throws Exception;

	Integer getCount() throws Exception;

	/**
	 * 获取所有公司的id和name集合
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllCompanyIdAndName() throws Exception;

}
