package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CustomerListService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

@Controller
@Scope("prototype")
@RequestMapping("/warrant")
public class WarrantReminderController extends BaseController {
	
	@Resource
	private DepartmentService departmentService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CustomerListService customerListService;
	
	@RequestMapping("getReminderList")
	public String getReminderList(Integer pageIndex,Integer pageSize,Integer initPage,
			String keyword,Model model,
			Integer dId,Integer eId,String beginDate,String endDate,Integer fllowType) throws Exception{
		fllowType = fllowType == null ? 0:fllowType;
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyword = (keyword==null?"":keyword);
		beginDate = formatNull(beginDate);
		endDate = formatNull(endDate);
		if(beginDate==null && endDate!=null){
			beginDate = endDate;
		}else if(beginDate!=null && endDate==null){
			endDate = beginDate;
		}
		
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		setEmployeeCacheMap(model);
		
		dId = (dId==null?0:dId);
		eId = (eId==null?0:eId);
		List<Department> departments = departmentService.getDeparmentByName("业务");
		
		//登陆对象
		Employee loginEmployee = getLoginEmployee();
		//登陆对象的角色ID
		Integer roleId = loginEmployee.getRole().getId();
		//用户角色类型,0:管理层/1:业务部经理/2:业务员
		Integer roleType;
		//工作提醒列表对象
		List<Map<String, Object>> customerReminders = new ArrayList<Map<String,Object>>();
		//总行数
		Integer count = 0;
		//员工ID集合
		List<Integer> employeeIds = new ArrayList<Integer>();
		
		if (Constant.warrantAdmin.equals(roleId)) {//管理层
			roleType = 0;
			if (eId>0) {
				employeeIds.add(eId);
			}else {
				if (dId>0) {
					Integer[] dids = new Integer[1];
					dids[0] = dId;
					List<Employee> employees = employeeService.getEmployeeByDIds(dids);
					if (employees!=null && employees.size()>0) {
						for (int i=0;i<employees.size();i++) {
							employeeIds.add(employees.get(i).getId());
						}
					}
				}else {
					employeeIds = employeeService.getEmployeeIds();
				}
			}
		}else if (Constant.warrantManager==roleId) {		//权证部门经理
			roleType = 1;
			getEmplloyByDids(model,new Integer[]{loginEmployee.getDepartment().getId()});
			if (eId>0) {
				employeeIds.add(eId);
			}else {
				//获取该部门下的所有员工
				employeeIds = employeeService.getEmployeeIdsByDId(loginEmployee.getDepartment().getId());
			}
		}else {											//专员
			roleType = 2;
			employeeIds.add(loginEmployee.getId());
		}
		Integer[] es = null;
		if (employeeIds!=null && employeeIds.size()>0) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		//获取列表
		customerReminders = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, es, Constant.reminderList, keyword,beginDate,endDate, "getWarrantReminderListByEmployeeIds",null,fllowType);
		//获取总行数
		count = customerListService.getCountByStateAndEId(Constant.reminderList, es, keyword,beginDate,endDate, "getWarrantReminderListCountByEmployeeIds",null,fllowType);
		System.out.println("customerReminders==============>");
		System.out.println(count);
		for (Map<String, Object> map : customerReminders) {
			System.out.println(map);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"warrant/getReminderList.do?initPage=1&keyword="+keyword
				+"&dId="+dId+"&eId="+eId+"&beginDate="+beginDate+"&endDate="+endDate+"&fllowType="+fllowType, "tableList");
		model.addAttribute("customerReminders", customerReminders);
		model.addAttribute("keyStr", keyword);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("eId", eId);
		model.addAttribute("dId", dId);
		model.addAttribute("fllowType", fllowType);
		model.addAttribute("departments", departments);
		model.addAttribute("roleId",roleId);
		
		// 获取所有部门信息并保存到中
		setDepartmentCacheMap(model);
		// 获取所有员工信息并保存到model中
		setEmployeeCacheMap(model);
		
		if (initPage==null) {
			return "page/warrantReminder/reminderListUI";
		}
		return "page/warrantReminder/reminderList";
	}
	
	private String formatNull(String str){
		if (str==null || "".equals(str) || "null".equals(str)) {
			return null;
		}
		return str;
	}
	
	private void getEmplloyByDids(Model model,Integer[] dIds) throws Exception{
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}
}