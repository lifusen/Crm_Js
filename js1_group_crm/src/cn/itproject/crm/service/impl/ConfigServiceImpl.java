package cn.itproject.crm.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Config;
import cn.itproject.crm.dao.ConfigDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ConfigService;

/**
 * 参数配置service实现类
 * 
 * @author MrLiu
 *
 */
@Service
public class ConfigServiceImpl extends BaseServiceSupport<Config> implements ConfigService {
	@Resource
	private ConfigDao configDao;

	@Override
	protected BaseDao<Config> getBaseDao() {
		return configDao;
	}
	
	@Override
	@Cacheable("systemConfigCache")
	public String getValue(String configKey) throws Exception{
		return configDao.getValue(configKey);
	}

	@Override
	@Cacheable("systemConfigCache")
	public Integer getCommonPoolDay() throws Exception {
		String commonPoolDayString = configDao.getValue(Config.COMMON_POOL_DAY);
		Integer commonPoolDay = null;	
		if (StringUtils.isNotBlank(commonPoolDayString)) {
			try {
				commonPoolDay = Integer.parseInt(commonPoolDayString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (commonPoolDay == null) {
			commonPoolDay = 50; // 默认为50天
		}
		return commonPoolDay;
	}
	
	@Override
	@Cacheable("systemConfigCache")
	public Map<String, String> getConfigMap() throws Exception {
		return configDao.getConfigMap();
	}
	
	@Override
	@CacheEvict(value="systemConfigCache",allEntries=true)
	public void updateConfig(Config config) throws Exception {
		configDao.updateConfig(config);
	}


}
