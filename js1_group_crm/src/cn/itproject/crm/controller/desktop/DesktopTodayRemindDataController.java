package cn.itproject.crm.controller.desktop;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.TodayRemindData;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.PropertieFactory;

@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopTodayRemindDataController extends BaseController{

	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CustomerFollowService customerFollowService;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String URL = "page/desktop/todayRemindData";
	
	private static final Integer CACHE_TIME = 5;
	
	@RequestMapping("/getTodayRemindData")
	public String getTodayRemindData(Model model) throws Exception{
		// 当前的登录用户ID
		Integer _loginEmployeeId = getLoginEmployee().getId();
		// redisKey
		String redisKey = this.getClass().getName()+"-getTodayRemindData-"+_loginEmployeeId;
		// 将数据存储到modelMap中
		Object redisValue = redisTemplate.opsForValue().get(redisKey);
		if (redisValue != null) {
			// 获取缓存的值
			@SuppressWarnings("unchecked")
			Map<String, Object> modelMap = (Map<String, Object>) redisValue;
			// 设置到model
			model.addAllAttributes(modelMap);
			return URL;
		}
		
		
		System.out.println("加载每日提醒数据....");
		Employee loginEmployee = getLoginEmployee();
		Integer employeeId = loginEmployee.getId();
		//创建今日提醒数据对象
		TodayRemindData todayRemindData = new TodayRemindData();
		//获取今日提醒数据
		Map<String, Object> remindMap = customerFollowService.getTodayRemindCountOfEmp(employeeId);
		//设置提醒人数
		todayRemindData.setRemind(Integer.parseInt(String.valueOf(remindMap.get("count"))));
		//获取今日还款提醒数据
		Map<String, Object> repaymentMap = customerFollowService.getTodayRepaymentCountOfEmp(employeeId);
		//设置还款人数
		todayRemindData.setRepayment(Integer.parseInt(String.valueOf(repaymentMap.get("count"))));
		//计算每日基础工作量
		Integer basicWorkTotal = Integer.parseInt(String.valueOf(PropertieFactory.getProVal(Constant.TODAY_BASIC_WORK_TOTAL)));
		Integer basicCount = basicWorkTotal-todayRemindData.getRemind()-todayRemindData.getRepayment();
		todayRemindData.setBasic(basicCount);
		
		
		Map<String, Object> modelMap = new HashMap<>();
		//存储每日基础数据
		modelMap.put("todayRemindData", todayRemindData);
		
		// 设置到model
		model.addAllAttributes(modelMap);
		// 设置redis缓存
		redisTemplate.opsForValue().set(redisKey, modelMap,CACHE_TIME,TimeUnit.MINUTES);
		
		return URL;
	}
	
}
