package cn.itproject.crm.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.itproject.crm.controller.base.BaseController;

@Controller
public class ShowRedisController extends BaseController{

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping("/redisView")
	public String redisView() {
		System.out.println("redisView...");
		return "page/redis/redisView";
	}
	
	@RequestMapping("/getRedisValue")
	public String getRedisValue(String key,String type,Model model) {
		Object value = null;
		if ("string".equals(type)) {
			value = redisTemplate.opsForValue().get(key);
		}else if("hash".equals(type)){
			value = redisTemplate.opsForHash().entries(key);
		}else if ("set".equals(type)) {
			value = redisTemplate.opsForSet().members(key);
		}else if ("zset".equals(type)) {
			value = redisTemplate.opsForZSet().range(key, 0, 10000);
		}
		if (value!=null) {
			String json = JSON.toJSONString(value,true);
			model.addAttribute("jsonValue", json);
		}
		model.addAttribute("value", value);
		return "page/redis/redisInfo";
	}
	
}
