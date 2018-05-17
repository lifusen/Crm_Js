package cn.itproject.crm.dao;

import java.util.Map;

import cn.itproject.crm.bean.Config;
import cn.itproject.crm.db.BaseDao;

/**
 * 参数配置
 * 
 * @author MrLiu
 *
 */
public interface ConfigDao extends BaseDao<Config> {
	/**
	 * 根据key获取value
	 * 
	 * @param configKey
	 * @return
	 * @throws Exception
	 */
	public String getValue(String configKey) throws Exception;

	
	/**
	 * 获取配置的key-value集合
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getConfigMap() throws Exception;
	
	/**
	 * 根据名称修改value
	 * @param config
	 * @throws Exception
	 */
	public void updateConfig(Config config) throws Exception;

}
