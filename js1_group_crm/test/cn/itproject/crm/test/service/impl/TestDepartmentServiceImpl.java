package cn.itproject.crm.test.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.test.BaseSpringTestSupport;

public class TestDepartmentServiceImpl extends BaseSpringTestSupport{

	@Resource
	private DepartmentService departmentService;
	
	@Test
	public void test() throws Exception {
		Map<Integer, String> map = departmentService.getAllDepartmentMap();
		System.out.println(map);
	}
}
