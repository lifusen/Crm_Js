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

import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.PlanData;
import cn.itproject.crm.controller.viewbean.VisitOrSignData;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.PlanService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.DateUtil;
import cn.itproject.crm.util.DoubleUtil;

/**
 * 上门签单进度汇总
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopVisitOrSignDataController extends BaseController {

	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CustomerFollowService customerFollowService;
	
	@Resource
	private PlanService planService;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String URL = "page/desktop/visitOrSignData";
	
	private static final Integer CACHE_TIME = 5;
	
	@RequestMapping("/getVisitOrSignData")
	public String getVisitOrSignData(Model model,Integer type) throws Exception{
		// 当前的登录用户ID
		Integer _loginEmployeeId = getLoginEmployee().getId();
		// redisKey
		String redisKey = this.getClass().getName()+"-getVisitOrSignData-"+type+"-"+_loginEmployeeId;
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
		Map<Integer,VisitOrSignData> data = new LinkedHashMap<Integer, VisitOrSignData>(); 
		
		//平均
		Integer visitSum = 0;
		Integer signSum = 0;
		Double planVistSum = 0d;
		Double planSignSum = 0d;
		Double visitPercentSum = 0d;
		Double signPercentSum = 0d;
		Double croSum = 0d;
		
		Integer totalRows = 0;
		
		//数据库数据
		List<Map<String, Object>> visitList = null;
		List<Map<String, Object>> signList = null;
		List<PlanData> planList = null;
		
		if (Constant.managerRoleIds.contains(roleId)) {			//管理层
			typeName = "业务部门";
			departments = departmentService.getDeparmentByName("业务");
			totalRows = departments.size();
			for (Department department : departments) {
				Integer id = department.getId();
				String name = department.getName();
				data.put(id, new VisitOrSignData(id,name));
			}
			
			//如果是月
			if (type==0) {//月
				//获取部门的数据
				visitList = customerFollowService.getVisitOrSignRemindTotalCountOfDept(CustomerFollow.VISIT,null);
				signList = customerFollowService.getVisitOrSignRemindTotalCountOfDept(CustomerFollow.SIGN, null);
				//获取部门月计划数据
				planList = planService.getDeptMonthPlan();
			}else {//日
				//获取部门的数据
				visitList = customerFollowService.getVisitOrSignRemindTotalCountOfDept(CustomerFollow.VISIT, DateUtil.getCurrentDate());
				signList = customerFollowService.getVisitOrSignRemindTotalCountOfDept(CustomerFollow.SIGN, DateUtil.getCurrentDate());
				//获取部门月计划数据
				planList = planService.getDeptDayPlan();
			}
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
				data.put(id, new VisitOrSignData(id,name));
				employeeIds.add(id);
			}
			
			if (type==0) {//月
				//获取员工的每月数据
				visitList = customerFollowService.getVisitOrSignRemindTotalCountOfEmp(CustomerFollow.VISIT, employeeIds, null);
				signList = customerFollowService.getVisitOrSignRemindTotalCountOfEmp(CustomerFollow.SIGN, employeeIds, null);
				//获取该部门下所有业务员的数据
				planList = planService.getEmpMonthPlan(dId);
			}else {//日
				//获取员工的每日数据
				visitList = customerFollowService.getVisitOrSignRemindTotalCountOfEmp(CustomerFollow.VISIT, employeeIds, DateUtil.getCurrentDate());
				signList = customerFollowService.getVisitOrSignRemindTotalCountOfEmp(CustomerFollow.SIGN, employeeIds, DateUtil.getCurrentDate());
				//获取该部门下所有业务员的数据
				planList = planService.getEmpDayPlan(dId);
			}
		}
		
		//构建已上门
		for (Map<String, Object> map : visitList) {
			Integer rid = Integer.valueOf(map.get("rid").toString());
			Integer count = Integer.valueOf(map.get("count").toString());
			VisitOrSignData vsd = data.get(rid);
			if (vsd!=null) {
				vsd.setVisit(count);
			}
			visitSum+=count;	//求和
		}
		
		//构建已签单
		for (Map<String, Object> map : signList) {
			Integer rid = Integer.valueOf(map.get("rid").toString());
			Integer count = Integer.valueOf(map.get("count").toString());
			VisitOrSignData vsd = data.get(rid);
			if (vsd!=null) {
				vsd.setSign(count);
			}
			signSum+=count;	//求和
		}

		//构建目标上门和目标签单
		for (PlanData planData : planList) {
			VisitOrSignData visitOrSignData = data.get(planData.getId());
			if (visitOrSignData==null) {
				continue;
			}
			//目标上门
			Double planVisit = planData.getVisit();
			visitOrSignData.setPlanVisit(planVisit);
			//目标签单
			Double planSign = planData.getSign();
			visitOrSignData.setPlanSign(planSign);
			//求和
			planVistSum+=planVisit;
			planSignSum+=planSign;
		}
		
		//计算完成率和转化率
		for (VisitOrSignData visitOrSignData : data.values()) {
			//获取上门和签单数据
			Double planVisit = visitOrSignData.getPlanVisit();
			Integer visit = visitOrSignData.getVisit();
			Double planSign = visitOrSignData.getPlanSign();
			Integer sign = visitOrSignData.getSign();
			
			Double visitPercent = 0d;	//上门完成率
			Double signPercent = 0d;	//签单完成率

			//上门完成率
			if (planVisit==0) {
				visitOrSignData.setVisitPercent(0d);
			}else {
				visitPercent = DoubleUtil.div(visit*100, planVisit, 2); 
				visitOrSignData.setVisitPercent(visitPercent);
			}
			//签单完成率
			if (planSign==0) {
				visitOrSignData.setSignPercent(0d);
			}else {
				signPercent = DoubleUtil.div(sign*100, planSign, 2); 
				visitOrSignData.setSignPercent(signPercent);
			}
			
			//计算转化率:已上门/已签到拿
			Double cro = 0d;
			if (visit==0) {
				visitOrSignData.setCro(0d);
			}else {
				cro = DoubleUtil.div(sign*100, visit,2);
				visitOrSignData.setCro(cro);
			}
			
			//求和
			visitPercentSum+=visitPercent;	//上门完成率总和
			signPercentSum+=signPercent;	//签单完成率总和
			croSum+=cro;					//转化率总和
		}
		
		
//		System.out.println(typeName+"\t已上门\t已签单");
//		for (Entry<Integer, VisitOrSignData> kv : data.entrySet()) {
//			VisitOrSignData v = kv.getValue();
//			System.out.println(v.getName()+"\t"+v.getVisit()+"\t"+v.getSign());
//		}
		
		//平均一栏
		Map<String, Object> mapAvg = new HashMap<String, Object>();
		
		Double visitAvg = DoubleUtil.div(visitSum, totalRows, 2);
		Double signAvg = DoubleUtil.div(signSum, totalRows,2);
		Double planVisitAvg = DoubleUtil.div(planVistSum, totalRows,2);
		Double planSignAvg = DoubleUtil.div(planSignSum, totalRows,2);
		Double visitPercentAvg = DoubleUtil.div(visitPercentSum, totalRows,2);
		Double signPercentAvg = DoubleUtil.div(signPercentSum, totalRows,2);
		Double croAvg = DoubleUtil.div(croSum, totalRows,2);
		mapAvg.put("visitAvg", visitAvg);
		mapAvg.put("signAvg", signAvg);
		mapAvg.put("planVisitAvg", planVisitAvg);
		mapAvg.put("planSignAvg", planSignAvg);
		mapAvg.put("visitPercentAvg", visitPercentAvg);
		mapAvg.put("signPercentAvg", signPercentAvg);
		mapAvg.put("croAvg", croAvg);
		
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("visitOrSignData", data);
		modelMap.put("typeName", typeName);
		modelMap.put("mapAvg", mapAvg);
		
		// 设置到model
		model.addAllAttributes(modelMap);
		// 设置redis缓存
		redisTemplate.opsForValue().set(redisKey, modelMap,CACHE_TIME,TimeUnit.MINUTES);
		
		return URL;
	}
	
}
