package cn.itproject.crm.test.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import cn.itproject.crm.dao.ConfigDao;
import cn.itproject.crm.test.BaseSpringTestSupport;

public class TestConfigDaoImpl extends BaseSpringTestSupport{

	@Resource
	private ConfigDao configDao;
	
	@Test
	public void getConfigMap() throws Exception {
		Map<String, String> map = configDao.getConfigMap();	
		System.out.println(map);
	}
}
