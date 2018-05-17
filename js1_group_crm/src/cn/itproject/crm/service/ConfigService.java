package cn.itproject.crm.service;

import java.util.Map;

import cn.itproject.crm.bean.Config;

/**
 * 参数配置service接口
 * 
 * @author MrLiu
 *
 */
public interface ConfigService extends BaseService<Config> {

	/**
	 * 根据key获取value
	 * 
	 * @param configKey
	 * @return
	 * @throws Exception
	 */
	public String getValue(String configKey) throws Exception;
	
	/**
	 * 获取公共池时间
	 * @return
	 * @throws Exception
	 */
	public Integer getCommonPoolDay() throws Exception;

	/**
	 * 获取所有配置参数的Map对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getConfigMap() throws Exception;

	/**
	 * 修改配置参数
	 * 
	 * @param config
	 * @throws Exception
	 */
	public void updateConfig(Config config) throws Exception;
}
