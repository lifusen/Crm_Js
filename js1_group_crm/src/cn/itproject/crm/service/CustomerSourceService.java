package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.CustomerSource;

/**
 * 客户来源业务接口
 * @author MrLiu
 *
 */
public interface CustomerSourceService extends BaseService<CustomerSource>{

	/**
	 * 获取所有客户来源的Map
	 * @return
	 * @throws Exception
	 */
	Map<Integer, String> getAllCustomerSourceMap() throws Exception;
	
	/**
	 * 获取所有客户来源的对象集合
	 * @return
	 * @throws Exception
	 */
	List<CustomerSource> getCustomerSourceList() throws Exception;
	
	/**
	 * 通过id获取客户来源
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CustomerSource getCustomerSource(Integer id) throws Exception;
	
	/**
	 * 保存客户来源
	 * @param customerSource
	 * @throws Exception
	 */
	void addCustomerSource(CustomerSource customerSource) throws Exception;
	
	/**
	 * 修改客户来源
	 * @param customerSource
	 * @throws Exception
	 */
	void updateCustomerSource(CustomerSource customerSource) throws Exception;
	
	/**
	 * 根据Id删除客户来源
	 * @param id
	 * @throws Exception
	 */
	void deleteCustomerSource(Integer id) throws Exception;

	/**
	 * 获取所有客户来源数量统计
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getCustomerSourcePie(String order) throws Exception;

}
