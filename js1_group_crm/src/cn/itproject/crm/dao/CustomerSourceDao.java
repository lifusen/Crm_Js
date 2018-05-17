package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.db.BaseDao;

/**
 * 客户来源DAO接口
 * @author MrLiu
 *
 */
public interface CustomerSourceDao extends BaseDao<CustomerSource>{

	Integer getIdByName(String sourceName) throws Exception;

	/**
	 * 获取所有客户来源id和name的集合
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllCustomerSourceIdAndName() throws Exception;

	List<Map<String, Object>> getCustomerSourcePie(String order) throws Exception;

}
