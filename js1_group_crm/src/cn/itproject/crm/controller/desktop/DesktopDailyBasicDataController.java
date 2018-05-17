package cn.itproject.crm.controller.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.DailyBasicData;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.DateUtil;
import cn.itproject.crm.util.DoubleUtil;

/**
 * 每日基础数据汇总
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopDailyBasicDataController extends BaseController{
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CustomerFollowService customerFollowService;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String URL = "page/desktop/dailyBasicData";
	
	private static final Integer CACHE_TIME = 5;
	
	@RequestMapping("/getDailyBasicData")
	public String getDailyBasicData(Model model) throws Exception{
		// 当前的登录用户ID
		Integer _loginEmployeeId = getLoginEmployee().getId();
		// redisKey
		String redisKey = this.getClass().getName()+"-getDailyBasicData-"+_loginEmployeeId;
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
		
		
		//获取当前登陆对象的角色
		Employee loginEmployee = getLoginEmployee();
		//角色ID
		Integer roleId = loginEmployee.getRole().getId();
		
		//所有业务部门集合
		List<Department> departments = null;
		
		//某部门下所有员工集合
		List<Employee> employees = null;

		//类型名称
		String typeName = null;
		
		//显示数据集合
		Map<Integer,DailyBasicData> data = new LinkedHashMap<Integer, DailyBasicData>(); 
		
		//平均
		Integer totalSum = 0;
		Integer todaySum = 0;
		Integer totalRows = 0;
		
		//数据库数据
		List<Map<String, Object>> totalList = null;
		List<Map<String, Object>> todayList = null;
		
		if (Constant.managerRoleIds.contains(roleId)) {			//管理层
			typeName = "业务部门";
			departments = departmentService.getDeparmentByName("业务");
			totalRows = departments.size();
			for (Department department : departments) {
				Integer id = department.getId();
				String name = department.getName();
				data.put(id, new DailyBasicData(id,name));
			}
			//获取部门的每日数据
			totalList = customerFollowService.getRemindTotalCountOfDept(null);	//总量
			todayList = customerFollowService.getRemindTotalCountOfDept(DateUtil.getCurrentDate());	//当天量
		}else if (Constant.businessManagerRoleId==roleId) {		//业务部门经理
			typeName = "客户经理";
			//部门ID
			Integer dId = loginEmployee.getDepartment().getId();
			//通过部门ID获取其所有业务员
			employees = employeeService.getEmployeeByDId(loginEmployee.getId(), dId);
			totalRows = employees.size();
			//业务员ID集合
			List<Integer> employeeIds = new ArrayList<Integer>();
			for (Employee employee : employees) {
				Integer id = employee.getId();
				String name = employee.getName();
				data.put(id, new DailyBasicData(id,name));
				employeeIds.add(id);
			}
			//获取员工的每日数据
			totalList = customerFollowService.getRemindTotalCountOfEmp(employeeIds, null);
			todayList = customerFollowService.getRemindTotalCountOfEmp(employeeIds, DateUtil.getCurrentDate());
		}
		
		//构建提醒总量
		for (Map<String, Object> map : totalList) {
			Integer rid = Integer.valueOf(map.get("rid").toString());
			Integer count = Integer.valueOf(map.get("count").toString());
			DailyBasicData dbd = data.get(rid);
			if (dbd!=null) {
				dbd.setTotal(count);	//所有总量
			}
			totalSum+=count;	//求和
		}
		
		//构建今日总量
		for (Map<String, Object> map : todayList) {
			Integer rid = Integer.valueOf(map.get("rid").toString());
			Integer count = Integer.valueOf(map.get("count").toString());
			DailyBasicData dbd = data.get(rid);
			if (dbd!=null) {
				dbd.setToday(count);	//今日总量
			}
			todaySum+=count;	//求和
		}
		
		
//		System.out.println(typeName+"\t总提醒量\t今日提醒");
//		for (Entry<Integer, DailyBasicData> kv : data.entrySet()) {
//			DailyBasicData v = kv.getValue();
//			System.out.println(v.getName()+"\t"+v.getTotal()+"\t"+v.getToday());
//		}
		
		//平均一栏
		Map<String, Object> mapAvg = new HashMap<String, Object>();
		
		Double totalAvg = DoubleUtil.div(totalSum, totalRows, 2);
		Double todayAvg = DoubleUtil.div(todaySum, totalRows,2);
		mapAvg.put("totalAvg", totalAvg);
		mapAvg.put("todayAvg", todayAvg);
		
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("dailyBasicData", data);
		modelMap.put("typeName", typeName);
		modelMap.put("mapAvg", mapAvg);
		
		// 设置到model
		model.addAllAttributes(modelMap);
		
		// 设置redis缓存
		redisTemplate.opsForValue().set(redisKey, modelMap,CACHE_TIME,TimeUnit.MINUTES);
		
		return URL;
	}
}
