package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.OutLoanCustomerService;
import cn.itproject.crm.util.Constant;

@Controller
@RequestMapping("/warranter")
public class WarrantOutLoanController extends BaseController{

	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private OutLoanCustomerService outLoanCustomerService;
	
	
	@RequestMapping("getOutLoanList")
	public String getOutLoanCustomerList(Integer pageIndex,Integer pageSize,
			Integer initPage,String keyword,Model model,
			Integer dId,Integer eId,String beginDate,String endDate) throws Exception{
		dId = (dId==null?0:dId);
		eId = (eId==null?0:eId);
		if (beginDate==null || endDate==null || 
				"".equals(beginDate) || "".equals(endDate)) {
			beginDate="";
			endDate="";
		}
		
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		setEmployeeCacheMap(model);
		
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		List<Department> departments = departmentService.getDeparmentByName("业务");
		keyword = (keyword==null?"":keyword);
		//登陆对象
		Employee loginEmployee = getLoginEmployee();
		//登陆对象的角色ID
		Integer roleId = loginEmployee.getRole().getId();
		//用户角色类型,0:管理层/1:业务部经理/2:业务员
		Integer roleType;
		//工作提醒列表对象
		List<Map<String, Object>> outLoanCustomers = new ArrayList<Map<String,Object>>();
		//总行数
		Integer count = 0;
		//员工ID集合
		List<Integer> employeeIds = new ArrayList<Integer>();
		
		if (Constant.managerRoleIds.contains(roleId) || Constant.warrantAdmin.equals(roleId)) {			//管理层
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
		}else if (Constant.businessManagerRoleId==roleId || Constant.warrantManager.equals(roleId)) {		//业务部门经理
			roleType = 1;
			if (eId>0) {
				employeeIds.add(eId);
			}else {
				//获取该部门下的所有员工
				employeeIds = employeeService.getEmployeeIdsByDId(loginEmployee.getDepartment().getId());
			}
			getEmplloyByDids(model,new Integer[]{loginEmployee.getDepartment().getId()});
		}else {											//业务员
			roleType = 2;
			employeeIds.add(loginEmployee.getId());
		}
		
		//获取列表
		outLoanCustomers = outLoanCustomerService.getOutLoanCustomerList(pageIndex,pageSize,employeeIds,keyword,beginDate,endDate);
		//获取总行数
		count = outLoanCustomerService.getOutLoanCustomerListCount(employeeIds,keyword,beginDate,endDate);
		System.out.println("outLoanCustomers==============>");
		System.out.println(count);
		for (Map<String, Object> map : outLoanCustomers) {
			System.out.println(map);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"warranter/getOutLoanList.do?initPage=1&keyword="+keyword+"&dId="+dId+"&eId="+eId+"&beginDate="+beginDate+"&endDate="+endDate, "tableList");
		model.addAttribute("outLoanCustomers", outLoanCustomers);
		model.addAttribute("keyStr", keyword);
		model.addAttribute("dId",dId);
		model.addAttribute("eId",eId);
		model.addAttribute("beginDate",beginDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("roleId",roleId);
		model.addAttribute("departments",departments);
		if (initPage==null) {
			return "page/warrantOutloan/listUI";
		}
		return "page/warrantOutloan/list";
	}
	private void getEmplloyByDids(Model model,Integer[] dIds) throws Exception{
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}
}
