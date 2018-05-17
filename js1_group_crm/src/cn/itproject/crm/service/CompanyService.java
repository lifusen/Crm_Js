package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Company;

public interface CompanyService extends BaseService<Company> {

	/**
	 * 获取公司的分页列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<Company> getCompanyList(Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 获取公司的总行数
	 */
	Integer getCompanyCount() throws Exception;
	
	/**
	 * 获取所有的公司对象集合
	 * @return
	 * @throws Exception
	 */
	List<Company> getAllCompany(Integer type) throws Exception;
	
	/**
	 * 获取所有公司信息的Map
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<Integer, String> getAllCompanyMap() throws Exception;

	/**
	 * 通过id获取公司
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Company getCompany(Integer id) throws Exception;
	
	/**
	 * 保存公司
	 * @param company
	 * @throws Exception
	 */
	void addCompany(Company company) throws Exception;
	
	/**
	 * 修改公司
	 * @param company
	 * @throws Exception
	 */
	void updateCompany(Company company) throws Exception;
	
	/**
	 * 根据Id删除公司
	 * @param id
	 * @throws Exception
	 */
	void deleteCompany(Integer id) throws Exception;

	/**
	 * 根据类型查询中心
	 * @param type 1表示业务中心，2表示权证中心
	 * @return
	 */
	List<Company> getCompanyByType(int type)throws Exception;

}
