package cn.itproject.crm.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.itproject.crm.bean.Message;
import cn.itproject.crm.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@TransactionConfiguration(defaultRollback=false)
public class MessageTest{

	@Resource
	private MessageService messageService;
	
	@Rollback(false)
	@Test
	public void test1() throws Exception {
		Message message = new Message();
		message.setTitle("测试");
		message.setContent("内容....");
		message.setType(0);
		messageService.saveEntity(message);
	}
}
