package cn.itproject.crm.test.redis;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.test.BaseSpringTestSupport;

public class TestRedisTemplate extends BaseSpringTestSupport{

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testSet() throws UnsupportedEncodingException {
		redisTemplate.opsForValue().set("hello", "Hello Redis...", 5,TimeUnit.MINUTES);
		Department department = new Department(1, "业务一部", "业务一部备注...");
		redisTemplate.opsForValue().set("department", department);
		redisTemplate.opsForValue().set("你好世界", "你好世界.....");
		redisTemplate.opsForValue().set("123你好世界", "你好世界.....");
	}
	
	@Test
	public void testGet() {
		Object object = redisTemplate.opsForValue().get("hello");
		System.out.println(object);
	}
}
