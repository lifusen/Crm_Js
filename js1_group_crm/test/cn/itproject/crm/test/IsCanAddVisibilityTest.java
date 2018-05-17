package cn.itproject.crm.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itproject.crm.bean.Message;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class IsCanAddVisibilityTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private CustomerFollowService customerFollowService;
	
	@Test
	public void test1() throws Exception {
		Boolean flag = customerFollowService.isCanAddVisibility(6);
		System.out.println(flag);
	}
}
