package cn.itproject.crm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.NotificationService;
import cn.itproject.crm.service.WarrantAllotService;
import cn.itproject.crm.util.Constant;

@Controller
@RequestMapping("/warrant")
public class WarrantAllotController extends BaseController{

	@Resource
	private WarrantAllotService warrantAllotService;
	
	@RequestMapping("allot")
	@ResponseBody
	public boolean allot(Integer companyId,Integer dId,Integer eId,String ids){
		try {
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			warrantAllotService.allot(employee,companyId,dId,eId,ids);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@RequestMapping("addAssistLoaner")
	@ResponseBody
	public boolean addAssistLoaner(OutLoanBelong outLoanBelong){
		try {
			return warrantAllotService.addAssistLoaner(outLoanBelong);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	@RequestMapping("removeAssistLoaner")
	@ResponseBody
	public boolean removeAssistLoaner(OutLoanBelong outLoanBelong){
		try {
			return warrantAllotService.removeAssistLoaner(outLoanBelong);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
}