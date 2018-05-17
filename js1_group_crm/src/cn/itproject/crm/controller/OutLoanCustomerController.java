package cn.itproject.crm.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.OutLoanCustomer;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.FailureMessageService;
import cn.itproject.crm.service.OutLoanCustomerService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

/**
 * 放款客户控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/outLoanCustomer")
public class OutLoanCustomerController extends BaseController{
	
	@Resource
	private OutLoanCustomerService outLoanCustomerService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private FailureMessageService failureMessageService;
	
	@RequestMapping("/addOutLoanCustomer")
	@ResponseBody
	public Boolean addOutLoanCustomer(OutLoanCustomer outLoanCustomer, Integer warranterId) throws Exception {
		System.out.println(outLoanCustomer);
		outLoanCustomerService.addOutLoanCustomer(outLoanCustomer,getLoginEmployee(),warranterId);
		return true;
	}
	
	@RequestMapping("/updateOutLoanCustomer")
	@ResponseBody
	public Boolean updateOutLoanCustomer(OutLoanCustomer outLoanCustomer,Integer customerId) throws Exception {
		System.out.println(outLoanCustomer);
		outLoanCustomerService.updateOutLoanCustomer(outLoanCustomer,getLoginEmployee(),customerId);
		return true;
	}
	

	/*@RequestMapping("/getUpdateOutLoanCustomerList")
	public String getUpdateOutLoanCustomerList(Integer pageIndex,Integer initPage,Integer customerId,Integer sId,Model model) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		*//**
		 * 如果是签到列表双击进入,有sId(签单客户ID),获取到该客户所有的签单ID,并指定sId对应的页数
		 * 如果是有效客户列表双击进入,分页查询
		 *//*
		Integer pageSize = 1;
		//获取到该客户下的所有签单ID
		List<Integer> outLoanCustomerIds = outLoanCustomerService.getOutLoanCustomerIdsByCId(customerId);
		//签单客户集合
		List<OutLoanCustomer> outLoanCustomers = new ArrayList<OutLoanCustomer>();
		//总数
		Integer count = 0;
		//是否有签单客户
		Boolean hasSign = false;
		if (outLoanCustomerIds==null||outLoanCustomerIds.size()==0) {
			hasSign = false;
		}else {
			hasSign = true;
		}
		
		//如果有签单客户
		if (hasSign) {
			count = outLoanCustomerIds.size();	//总行数
			if (initPage==null&&sId!=null) {//签单客户列表双击进入
				//1,5,6 
				//6:索引为2,页码为3
				pageIndex = outLoanCustomerIds.indexOf(sId)+1;
			}
			//获取分页列表
			outLoanCustomers = outLoanCustomerService.getOutLoanCustomerByCustomerId(pageIndex,pageSize,customerId);
			
		}
		//签单客户
		model.addAttribute("outLoanCustomers", outLoanCustomers);					
		//构建参数
		builderParam(model, pageIndex, pageSize, count, 
				"outLoanCustomer/getUpdateOutLoanCustomerList.do?initPage=1&customerId="+customerId, "updateOutLoanCustomerListArea");
		return "page/outLoanCustomer/listAndUpdate";
	}*/
	
	@RequestMapping("getOutLoanCustomerList")
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
		
		if (Constant.managerRoleIds.contains(roleId)) {			//管理层
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
		}else if (Constant.businessManagerRoleId==roleId) {		//业务部门经理
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
				"outLoanCustomer/getOutLoanCustomerList.do?initPage=1&keyword="+keyword+"&dId="+dId+"&eId="+eId+"&beginDate="+beginDate+"&endDate="+endDate, "tableList");
		model.addAttribute("outLoanCustomers", outLoanCustomers);
		model.addAttribute("keyStr", keyword);
		model.addAttribute("dId",dId);
		model.addAttribute("eId",eId);
		model.addAttribute("beginDate",beginDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("roleId",roleId);
		model.addAttribute("departments",departments);
		if (initPage==null) {
			return "page/outLoanCustomer/listUI";
		}
		return "page/outLoanCustomer/list";
	}
	private void getEmplloyByDids(Model model,Integer[] dIds) throws Exception{
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}
	
	@RequestMapping("/getOutLoanCustomerSummaryInfo")
	@ResponseBody
	public List<Map<String, Object>> getOutLoanCustomerSummaryInfo(Integer signCustomerId) throws Exception{
		List<Map<String, Object>> list = outLoanCustomerService.getOutLoanCustomerSummaryInfo(signCustomerId);
		return list;
	}
	
	@RequestMapping("/getUpdateOutLoanCustomerList")
	public String getUpdateOutLoanCustomerList(Integer pageIndex,Integer initPage,Integer customerId,Integer sId,Integer oId, Model model, String refererUrl) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		/**
		 * 如果是放款列表双击进入,有sId(签单客户ID),oId(放款客户ID)，获取到该客户所有的放款ID,并指定sId对应的页数
		 * 如果是有效客户列表双击进入,分页查询
		 */
		//System.out.println(pageIndex+"+"+initPage+"+"+customerId+"+"+sId+"+"+oId+"+======================");
		Integer pageSize = 1;
		//获取到该签单下的所有放款ID，该方法未实现
		List<Integer> outLoanCustomerIds = outLoanCustomerService.getOutLoanCustomerIdsByCId(customerId);
		//签单客户集合
		List<OutLoanCustomer> outLoanCustomers = new ArrayList<OutLoanCustomer>();
		//总数
		Integer count = 0;
		//是否有放款客户
		Boolean hasLoan = false;
		OutLoanCustomer outLoanCustomer = outLoanCustomerService.getOutLoanCustomerById(oId);
		//System.out.println(outLoanCustomer.getSignCustomer()+"============123===============");
		if (outLoanCustomerIds==null||outLoanCustomerIds.size()==0) {
			
		}else {
			hasLoan = true;
		}
		
		//如果有放款客户
		if (hasLoan) {
			count = outLoanCustomerIds.size();	//总行数
			if (initPage==null&&sId!=null) {//签单客户列表双击进入
				//1,5,6 
				//6:索引为2,页码为3
				pageIndex = outLoanCustomerIds.indexOf(sId)+1;
			}
			//获取分页列表
			outLoanCustomers = outLoanCustomerService.getOutLoanCustomerByCustomerId(pageIndex,pageSize,customerId);
			
		}
		//签单客户
		model.addAttribute("outLoanCustomer", outLoanCustomer);	
		model.addAttribute("refererUrl", refererUrl);
		//构建参数
		builderParam(model, pageIndex, pageSize, count, 
				"outLoanCustomer/getUpdateOutLoanCustomerList.do?initPage=1&signCustomerId="+sId, "updateOutLoanCustomerListArea");
		return "page/outLoanCustomer/outLoanInfoForm";
	}
	
}
