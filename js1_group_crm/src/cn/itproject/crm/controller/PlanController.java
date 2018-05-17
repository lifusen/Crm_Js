package cn.itproject.crm.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Plan;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.PlanService;
import cn.itproject.crm.util.Constant;

/**
 * 计划控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/plan")
public class PlanController extends BaseController{
	
	@Resource
	private PlanService planService;
	
	@RequestMapping("/gotoPlanUI")
	public String gotoPlanUI(Model model) throws Exception {
		Integer type = null;
		Employee loginEmployee = getLoginEmployee();
		Integer roleId = loginEmployee.getRole().getId();
		if (Constant.administrationRoleIds.contains(roleId)) {
			
		}else if (Constant.businessManagerRoleId==roleId) {
			type = Plan.DEPT;
		}else {
			type = Plan.EMP;
		}
		handlerType(model, type);
		
		Plan plan = planService.getPlan(loginEmployee.getId());
		if (plan==null) {	//当月未添加计划
			return "page/plan/add";
		}
		model.addAttribute("plan", plan);
		return "page/plan/update";
		
	}
	
	/**
	 * 处理类型
	 * @param model
	 * @param type
	 */
	private void handlerType(Model model,Integer type){
		String typeName = "";
		switch (type) {
		case 0:
			typeName="部门";
			break;
		case 1:
			typeName="";
			break;
		default:
			break;
		}
		model.addAttribute("type", type);
		model.addAttribute("typeName", typeName);
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Boolean add(Plan plan) throws Exception {
		try {
			//获取登录对象
			Employee employee = getLoginEmployee();
			//设置操作人
			plan.setEmployee(employee);
			plan.setDepartment(employee.getDepartment());
			//保存
			planService.saveEntity(plan);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/gotoUpdate")
	public String gotoUpdate(Model model,Integer id,Integer type) throws Exception {
		Plan plan = planService.getEntity(id);
		model.addAttribute("plan", plan);
		handlerType(model, type);
		return "page/plan/update";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(Plan plan) throws Exception {
		try {
			Plan updatePlan = planService.getEntity(plan.getId());
			updatePlan.setVisit(plan.getVisit());
			updatePlan.setSign(plan.getSign());
			updatePlan.setPerformance(plan.getPerformance());
			updatePlan.setUpdateTime(new Date());
			
			planService.updateEntity(updatePlan);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
