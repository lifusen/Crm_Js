package cn.itproject.crm.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Workday;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.WorkdayService;

/**
 * 工作日控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/workday")
public class WorkdayController extends BaseController{
	
	@Resource
	private WorkdayService workdayService;
	
	@RequestMapping("gotoWorkdayUI")
	public String gotoWorkdayUI(Model model) throws Exception {
		Workday workday = workdayService.getCurrentMonthWorkDay();
		//添加
		if (workday==null) {
			return "page/workday/add";
		}
		//修改
		model.addAttribute("workday", workday);
		return "page/workday/update";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Boolean add(Workday workday) throws Exception {
		try {
			//获取登录对象
			Employee employee = getLoginEmployee();
			//设置操作人
			workday.setEmployee(employee);
			//保存
			workdayService.saveEntity(workday);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(Workday workday) throws Exception {
		try {
			Workday updateWorkday = workdayService.getCurrentMonthWorkDay();
			updateWorkday.setDateString(workday.getDateString());
			updateWorkday.setDay(workday.getDay());
			updateWorkday.setUpdateTime(new Date());
			workdayService.updateEntity(updateWorkday);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
