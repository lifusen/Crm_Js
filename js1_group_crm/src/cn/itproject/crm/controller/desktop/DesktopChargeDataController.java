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
import cn.itproject.crm.controller.viewbean.ChargeData;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

/**
 * 收费数据
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopChargeDataController extends BaseController{

	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CustomerFollowService customerFollowService;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String URL = "page/desktop/chargeData";
	
	private static final Integer CACHE_TIME = 5;
	
	@RequestMapping("/getChargeData")
	public String getChargeData(Model model,String totalOrMonth) throws Exception{
		// 当前的登录用户ID
		Integer _loginEmployeeId = getLoginEmployee().getId();
		// redisKey
		String redisKey = this.getClass().getName()+"-getChargeData-"+totalOrMonth+"-"+_loginEmployeeId;
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
		
		if (totalOrMonth==null || totalOrMonth.equals("")) {
			totalOrMonth = "total";
		}
		//类型名称
		String typeName = null;
		Employee loginEmployee = getLoginEmployee();
		Integer roleId = loginEmployee.getRole().getId();
		List<Map<String, Object>> chargeData = null;
		if (Constant.managerRoleIds.contains(roleId)) {					//管理层
			typeName="业务部门";
			chargeData = customerFollowService.getChargeDataForDemp(totalOrMonth,"getTollDataPercentOfDept");
		}else if (Constant.businessManagerRoleId==roleId) {
			typeName="客户经理";
			Integer[] ids = new Integer[1];
			ids[0] = loginEmployee.getDepartment().getId();
			List<Employee> employees = employeeService.getEmployeeByDIds(ids);
			List<Integer> eIds = new ArrayList<>();
			for (Employee employee:employees) {
				eIds.add(employee.getId());
			}
			chargeData = customerFollowService.getChargeDataForEmp(totalOrMonth,"getTollDataPercentOfEmp",eIds);
		}
		List<ChargeData> chargeDatas = new ArrayList<>();
		ChargeData sumData = new ChargeData();
		// 处理数据
		for (Map<String, Object> map:chargeData) {
			Integer rId = Integer.parseInt(map.get("rid").toString());
			ChargeData cd = new ChargeData(rId);
			String loanType = (String) map.get("loanType");
			String pStr = map.get("percent").toString();
			Double percent = new Double(pStr);
			if (chargeDatas.contains(cd)) {
				int index = chargeDatas.indexOf(cd);
				ChargeData c = chargeDatas.get(index);
				setScale(c,loanType,percent,sumData);
			}else {
				String name = map.get("rname").toString();
				cd.setName(name);
				setScale(cd, loanType, percent,sumData);
				chargeDatas.add(cd);
			}
		}
		ChargeData average = new ChargeData();
		average.setName("平均");
		int size = chargeDatas.size();
		BigDecimal b = null;
		if (sumData.getCreditCard()==0d) {
			average.setCreditCard(0d);
		}else {
			b = new BigDecimal(sumData.getCreditCard()/size);
			average.setCreditCard(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if (sumData.getBriefLoan()==0d) {
			average.setBriefLoan(0d);
		}else {
			b = new BigDecimal(sumData.getBriefLoan()/size);
			average.setBriefLoan(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if (sumData.getCarLoan()==0d) {
			average.setCarLoan(0d);
		}else {
			b = new BigDecimal(sumData.getCarLoan()/size);
			average.setCarLoan(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if (sumData.getCorpLoan()==0d) {
			average.setCorpLoan(0d);
		}else {
			b = new BigDecimal(sumData.getCorpLoan()/size);
			average.setCorpLoan(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if (sumData.getCreditLoan()==0d) {
			average.setCreditLoan(0d);
		}else {
			b = new BigDecimal(sumData.getCreditLoan()/size);
			average.setCreditLoan(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if (sumData.getHouseLoan()==0d) {
			average.setHouseLoan(0d);
		}else {
			b = new BigDecimal(sumData.getHouseLoan()/size);
			average.setHouseLoan(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if (sumData.getTwoLoan()==0d) {
			average.setTwoLoan(0d);
		}else {
			b = new BigDecimal(sumData.getTwoLoan()/size);
			average.setTwoLoan(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		// 求平均数
		chargeDatas.add(average);
		
		// 将数据存储到modelMap中
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("chargeData", chargeDatas);
		modelMap.put("typeName", typeName);
		// 设置到model
		model.addAllAttributes(modelMap);
		
		// 设置redis缓存
		redisTemplate.opsForValue().set(redisKey, modelMap,CACHE_TIME,TimeUnit.MINUTES);
		
		return URL;
	}
	
	private void setScale(ChargeData c, String loanType, Double percent,ChargeData sumData) {
		if ("信贷".equals(loanType)) {
			c.setCreditLoan(percent);
			double creditLoan = sumData.getCreditLoan();
			sumData.setCreditLoan(creditLoan+percent);
		}else if ("房贷".equals(loanType)) {
			c.setHouseLoan(percent);
			double houseLoan = sumData.getHouseLoan();
			sumData.setHouseLoan(houseLoan+percent);
		}else if ("二抵".equals(loanType)) {
			c.setTwoLoan(percent);
			double twoLoan = sumData.getTwoLoan();
			sumData.setTwoLoan(twoLoan+percent);
		}else if ("企贷".equals(loanType)) {
			c.setCorpLoan(percent);
			double corpLoan = sumData.getCorpLoan();
			sumData.setCorpLoan(corpLoan+percent);
		}else if ("信用卡".equals(loanType)) {
			c.setCreditCard(percent);
			double creditCard = sumData.getCreditCard();
			sumData.setCreditCard(creditCard+percent);
		}else if ("车贷".equals(loanType)) {
			c.setCarLoan(percent);
			double carLoan = sumData.getCarLoan();
			sumData.setCarLoan(carLoan+percent);
		}else if ("短借".equals(loanType)) {
			c.setBriefLoan(percent);
			double briefLoan = sumData.getBriefLoan();
			sumData.setBriefLoan(briefLoan+percent);
		}
	}
	
}
