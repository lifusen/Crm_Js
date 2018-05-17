package cn.itproject.crm.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CustomerListService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.FailureMessageService;
import cn.itproject.crm.util.Constant;

/**
 * 退单退件控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/warrant")
public class WarrantRejectController extends BaseController{
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private FailureMessageService failureMessageService;
	
	@Resource
	private CustomerListService customerListService;
	
	@Resource
	private DepartmentService departmentService;
	
	@RequestMapping("getRejectList")
	public String getRejectCustomerList(Integer pageIndex,Integer pageSize,
			Integer initPage,String keyword,Model model
			,Integer state,
			Integer dId,Integer eId,String beginDate,String endDate,String failureMessage) throws Exception{
		
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		setEmployeeCacheMap(model);
		
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyword = (keyword==null?"":keyword);
		state = (state==null?3:state);
		dId = (dId==null?0:dId);
		eId = (eId==null?0:eId);
		beginDate = formatNull(beginDate);
		endDate = formatNull(endDate);
		if(beginDate==null && endDate!=null){
			beginDate = endDate;
		}else if(beginDate!=null && endDate==null){
			endDate = beginDate;
		}
		if (failureMessage==null || "null".equals(failureMessage) || "".equals(failureMessage)) {
			failureMessage=null;
		}
		List<Department> departments = departmentService.getDeparmentByName("业务");
		List<Integer> stateList = null;
		if (state==1) {
			stateList = Arrays.asList(
					CustomerState.chargeback.ordinal());
		}else if (state==2) {
			stateList = Arrays.asList(
					CustomerState.reject.ordinal());
		}else if (state==3) {
			stateList = Arrays.asList(
					CustomerState.chargeback.ordinal(),
					CustomerState.reject.ordinal());
		}
		
		
		//登陆对象
		Employee loginEmployee = getLoginEmployee();
		//登陆对象的角色ID
		Integer roleId = loginEmployee.getRole().getId();
		//工作提醒列表对象
		List<Map<String, Object>> rejectCustomers = new ArrayList<Map<String,Object>>();
		//总行数
		Integer count = 0;
		//员工ID集合
		List<Integer> employeeIds = new ArrayList<Integer>();
		
		if (Constant.managerRoleIds.contains(roleId) || Constant.warrantAdmin.equals(roleId)) {//管理层
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
		}else if (Constant.businessManagerRoleId==roleId || Constant.warrantManager.equals(roleId)) {//业务部门经理
			
			if (eId>0) {
				employeeIds.add(eId);
			}else {
				//获取该部门下的所有员工
				employeeIds = employeeService.getEmployeeIdsByDId(loginEmployee.getDepartment().getId());
			}
			getEmplloyByDids(model,new Integer[]{loginEmployee.getDepartment().getId()});
		}else {											//业务员
			employeeIds.add(loginEmployee.getId());
		}
		Integer[] es = null;
		if (employeeIds!=null) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		//获取列表
		rejectCustomers = customerListService.getCustomersByStateAndEId(pageIndex, 
				pageSize, es, stateList, keyword,beginDate,endDate, 
				"getRejectCustomerListByEmployeeIds",failureMessage,null);
		//获取总行数
		count = customerListService.getCountByStateAndEId(stateList, es, keyword,
				beginDate,endDate, "getRejectCustomerListCountByEmployees",failureMessage,null);
		System.out.println(count);
		for (Map<String, Object> map : rejectCustomers) {
			System.out.println(map);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"warrant/getRejectList.do?initPage=1&keyword="+keyword
				+"&state="+state+"&dId="+dId+"&eId="+eId+"&beginDate="+beginDate+
				"&endDate="+endDate+"&failureMessage="+failureMessage, "tableList");
		model.addAttribute("rejectCustomers", rejectCustomers);
		model.addAttribute("keyStr", keyword);
		model.addAttribute("departments",departments);
		model.addAttribute("roleId", roleId);
		
		
		model.addAttribute("dId",dId);
		model.addAttribute("eId",eId);
		model.addAttribute("state",state);
		model.addAttribute("beginDate",beginDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("failureMessage",failureMessage);
		
		if (initPage==null) {
			return "page/warrantReject/listUI";
		}
		return "page/warrantReject/list";
	}
	private void getEmplloyByDids(Model model,Integer[] dIds) throws Exception{
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}
	
	private String formatNull(String str){
		if (str==null || "".equals(str) || "null".equals(str)) {
			return null;
		}
		return str;
	}
}
