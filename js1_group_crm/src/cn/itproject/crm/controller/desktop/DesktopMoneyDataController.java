package cn.itproject.crm.controller.desktop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import cn.itproject.crm.controller.viewbean.MoneyData;
import cn.itproject.crm.controller.viewbean.PlanData;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.PlanService;
import cn.itproject.crm.util.Constant;

/**
 * 收款数据
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopMoneyDataController extends BaseController{
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CustomerFollowService customerFollowService;
	
	@Resource
	private PlanService planService;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String URL = "page/desktop/moneyData";
	
	private static final Integer CACHE_TIME = 5;
	
	@RequestMapping("/getMoneyData")
	public String getMoneyData(Model model,String totalOrMonth) throws Exception{
		// 当前的登录用户ID
		Integer _loginEmployeeId = getLoginEmployee().getId();
		// redisKey
		String redisKey = this.getClass().getName()+"-getMoneyData-"+totalOrMonth+"-"+_loginEmployeeId;
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
		
		
		if (totalOrMonth==null || "".equals(totalOrMonth)) {
			totalOrMonth = "total";
		}
		//类型名称
		String typeName = null;
		Employee loginEmployee = getLoginEmployee();
		Integer roleId = loginEmployee.getRole().getId();
		List<Map<String, Object>> moneyData = null;
		List<PlanData> planDatas = null;
		if (Constant.managerRoleIds.contains(roleId)) {					//管理层
			typeName = "业务部门";
			moneyData = customerFollowService.getMoneyDataForDemp(totalOrMonth,"getGatheringDataPercentOfDept");
			if("total".equals(totalOrMonth)){
				planDatas = planService.getDeptTotalPlan();
			}else {
				planDatas = planService.getDeptMonthPlan();
			}
		}else if (Constant.businessManagerRoleId==roleId) {		
			typeName = "客户经理";
			Integer[] ids = new Integer[1];
			ids[0] = loginEmployee.getDepartment().getId();
			List<Employee> employees = employeeService.getEmployeeByDIds(ids);
			List<Integer> eIds = new ArrayList<>();
			for (Employee employee:employees) {
				eIds.add(employee.getId());
			}
			moneyData = customerFollowService.getMoneyDataForEmp(totalOrMonth,"getGatheringDataPercentOfEmp",eIds);
			
			if("total".equals(totalOrMonth)){
				planDatas = planService.getEmpTotalPlan(ids[0]);
			}else {
				planDatas = planService.getEmpMonthPlan(ids[0]);
			}
		}
		
		
		List<MoneyData> moneyDatas = new ArrayList<>();
		MoneyData sumData = new MoneyData();
		// 处理数据
		for (Map<String, Object> map:moneyData) {
			Integer rId = Integer.parseInt(map.get("rid").toString());
			String loanAmountTotalStr = map.get("loanAmountTotal").toString();
			Double loanAmountTotal = new Double(loanAmountTotalStr);
			String netEarningsTotalStr = map.get("netEarningsTotal").toString();
			Double netEarningsTotal = new Double(netEarningsTotalStr);
			MoneyData md = new MoneyData(rId);
			if (moneyDatas.contains(md)) {
				int index = moneyDatas.indexOf(md);
				MoneyData m = moneyDatas.get(index);
				setAmount(m,loanAmountTotal,netEarningsTotal,0.0,sumData);
			}else {
				String name = map.get("rname").toString();
				md.setName(name);
				for (PlanData planData:planDatas) {
					if (md.getId()==planData.getId()) {
						md.setGoal(planData.getPerformance());
						break;
					}
				}
				moneyDatas.add(md);
				setAmount(md,loanAmountTotal,netEarningsTotal,md.getGoal(),sumData);
			}
		}
		MoneyData average = new MoneyData();
		average.setName("平均");
		int size = moneyDatas.size();
		if (size>0) {
			BigDecimal b = new BigDecimal(sumData.getLoanAmountTotal()/size);
			average.setLoanAmountTotal(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(sumData.getNetEarningsTotal()/size);
			average.setNetEarningsTotal(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(sumData.getFinishPercent()/size);
			average.setFinishPercent(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			b = new BigDecimal(sumData.getGoal()/size);
			average.setGoal(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			moneyDatas.add(average);
		}
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("moneyData", moneyDatas);
		modelMap.put("typeName", typeName);
		
		// 设置model
		model.addAllAttributes(modelMap);
		
		// 设置redis缓存
		redisTemplate.opsForValue().set(redisKey, modelMap,CACHE_TIME,TimeUnit.MINUTES);
		
		return URL;
	}
	
	private void setAmount(MoneyData m, Double loanAmountTotal,
			Double netEarningsTotal,Double goal,MoneyData sumData) {
		double percent = 0.0;
		if (m.getGoal()>0) {
			BigDecimal b = new BigDecimal(netEarningsTotal/m.getGoal()*100);
			percent = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		sumData.setLoanAmountTotal(sumData.getLoanAmountTotal()+loanAmountTotal);
		sumData.setNetEarningsTotal(sumData.getNetEarningsTotal()+netEarningsTotal);
		sumData.setFinishPercent(sumData.getFinishPercent()+percent);
		sumData.setGoal(sumData.getGoal()+goal);
		m.setLoanAmountTotal(loanAmountTotal);
		m.setNetEarningsTotal(netEarningsTotal);
		m.setFinishPercent(percent);
	}
}
