package cn.itproject.crm.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
		@ContextConfiguration(name = "parent", locations = "classpath*:applicationContext*.xml"),
		@ContextConfiguration(name = "child", locations = "classpath*:spring-servlet.xml") })
public class BaseSpringTestSupport extends AbstractTransactionalJUnit4SpringContextTests {
	
}
