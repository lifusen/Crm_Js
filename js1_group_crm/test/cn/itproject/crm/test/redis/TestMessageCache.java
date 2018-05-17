package cn.itproject.crm.test.redis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import cn.itproject.crm.bean.Message;
import cn.itproject.crm.service.MessageService;
import cn.itproject.crm.test.BaseSpringTestSupport;

public class TestMessageCache extends BaseSpringTestSupport{

	@Resource
	private MessageService messageService;
	@Test
	public void testList() throws Exception {
		List<Message> employees = messageService.getMessages(0, 1, 10);
		for (Message message : employees) {
			System.out.println(message);
		}
	}
}
