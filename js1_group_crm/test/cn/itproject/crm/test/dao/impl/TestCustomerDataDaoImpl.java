package cn.itproject.crm.test.dao.impl;

import javax.annotation.Resource;

import org.junit.Test;

import cn.itproject.crm.dao.CustomerDataDao;
import cn.itproject.crm.test.BaseSpringTestSupport;

public class TestCustomerDataDaoImpl extends BaseSpringTestSupport {

	@Resource
	private CustomerDataDao customerDataDao;

	@Test
	public void test() throws Exception {
		Integer departmentId = 2;
		Integer employeeId = 10;
		String customerLevel = "优";
		String attentionLevel = "A";
		customerDataDao.getCustomerList(departmentId, employeeId, customerLevel, attentionLevel);
	}
}
