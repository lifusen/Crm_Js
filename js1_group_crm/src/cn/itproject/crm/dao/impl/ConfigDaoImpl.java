package cn.itproject.crm.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Config;
import cn.itproject.crm.dao.ConfigDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

/**
 * 参数配置dao实现类
 * 
 * @author MrLiu
 *
 */
@Repository
public class ConfigDaoImpl extends BaseDaoSupport<Config> implements ConfigDao {
	
	@Override
	public String getValue(String configKey) throws Exception {
		String sql = "select value from config where configKey = :configKey";
		Query query = getSession().createSQLQuery(sql);
		query.setString("configKey", configKey);
		return (String) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getConfigMap() throws Exception {
		String hql = "from Config";
		Query query = getSession().createQuery(hql);
		List<Config> configs = query.list();
		Map<String, String> map = new HashMap<>();
		if (configs!=null) {
			for (Config config : configs) {
				map.put(config.getConfigKey(), config.getValue());
			}
		}
		return map;
	}

	@Override
	public void updateConfig(Config config) throws Exception {
		String sql = "update config set value = :value,updateTime = :updateTime,updaterId=:updaterId,updaterName=:updaterName where configKey = :configKey";
		Query query = getSession().createSQLQuery(sql);
		query.setString("value", config.getValue());
		query.setTimestamp("updateTime", new Date());
		query.setInteger("updaterId", config.getUpdaterId());
		query.setString("updaterName", config.getUpdaterName());
		query.setString("configKey", config.getConfigKey());
		query.executeUpdate();
	}

}
