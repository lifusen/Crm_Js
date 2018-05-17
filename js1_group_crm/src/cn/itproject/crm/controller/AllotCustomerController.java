package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.bean.AllotCustomer.AllotType;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Customer.CustomerAddType;
import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.ShowRoster;
import cn.itproject.crm.service.AllotCustomerService;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerRosterService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

/**
 * 分配客户
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class AllotCustomerController extends BaseController{
	
	@Resource
	private CustomerService customerService;
	@Resource
	private ContactsService contactsService;
	@Resource
	private CustomerFollowService customerFollowService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private AllotCustomerService allotCustomerService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CustomerRosterService customerRosterService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("getAllotCustomerList")
	public String getAllotCustomerList(Integer pageIndex,Integer pageSize,
			Model model,Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		List<Department> departments = departmentService.getDeparmentByName("业务");
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(1));
		}
		
		// 如果当前user的角色是（超级管理员、销售总监、总经理、市场部经理）就查询全部
		if (Constant.managerRoleIds.contains(roleId) || (StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			customers = allotCustomerService.getAllotToDepartmentList(pageIndex, pageSize,null,keyStr);
			count = allotCustomerService.getAllotToDepartmentCount(null,keyStr);
		}else {
			customers = allotCustomerService.getAllotToDepartmentList(pageIndex, pageSize,employee.getId(),keyStr);
			count = allotCustomerService.getAllotToDepartmentCount(employee.getId(),keyStr);
		}
				
		if (initPage==null) {
			List<CustomerRoster> rosters = null;
			if (Constant.managerRoleIds.contains(roleId) || (StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
				rosters = customerRosterService.getListByState(null,null);
			}else {
				rosters = customerRosterService.getListByState(null,employee.getId());
			}
			
			List<ShowRoster> showRosters = new ArrayList<>();
			if (rosters!=null && rosters.size()>0) {
				for (CustomerRoster roster:rosters) {
					Integer rosterCount = customerService.getcountByAddType(CustomerAddType.excel,roster.getId());
					if (rosterCount==null || rosterCount==0) {
						continue;
					}
					showRosters.add(new ShowRoster(roster,rosterCount));
				}
			}
			model.addAttribute("rosters", showRosters);
		}
		model.addAttribute("keyStr", keyStr==null?"":keyStr);
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getAllotCustomerList.do?initPage=1", "tableList");
		model.addAttribute("departments", departments);
		model.addAttribute("customers", customers);
		if (initPage==null) {
			return "page/customer/allotToDepartment";
		}
		return "page/customer/allotToDepartment-List";
	}
	
	
	@RequestMapping("allotToDepartment")
	@ResponseBody
	public Boolean allotToDepartment(Integer companyId,Integer cId,Integer dId){
		Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		AllotCustomer allotCustomer = new AllotCustomer();
		Customer customer = new Customer();
		customer.setId(cId);
		Department department = new Department();
		department.setId(dId);
		
		allotCustomer.setCompanyId(companyId);
		allotCustomer.setCustomer(customer);
		allotCustomer.setDepartment(department);
		allotCustomer.setAllotType(AllotType.TO_DEPARTMENT.ordinal());
		allotCustomer.setOperation(nowEmployee);
		try {
			allotCustomerService.allotToDepartment(allotCustomer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("batchAllotToDepartment")
	@ResponseBody
	public Boolean batchAllotToDepartment(Integer companyId,Integer dId,String ids){
		if (ids==null || ids.equals("")) {
			return false;
		}
		String[] idArray = ids.split(",");
		Integer[] idArrayInt = new Integer[idArray.length];
		for (int i = 0; i < idArray.length; i++) {
			idArrayInt[i] = Integer.parseInt(idArray[i]);
		}
		Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		try {
			allotCustomerService.batchAllotToDepartment(companyId,dId,idArrayInt,nowEmployee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	@RequestMapping("allotToEmployee")
	@ResponseBody
	public String allotToEmployee(Integer cId,Integer eId){
		try {
			Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			
			//判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
			Integer num = allotCustomerService.getCustomerIsOverrun(eId, 1);
			if(num > 0){
				return num+""; 
			}else {
				allotCustomerService.allotToEmployee(cId,eId,nowEmployee);
			}
			
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	@RequestMapping("batchAllotToEmployee")
	@ResponseBody
	public String batchAllotToEmployee(String ids,Integer eId){
		try {
			if (ids==null || ids.equals("")) {
				return "false";
			}
			String[] idArray = ids.split(",");
			Integer[] idArrayInt = new Integer[idArray.length];
			for (int i = 0; i < idArray.length; i++) {
				idArrayInt[i] = Integer.parseInt(idArray[i]);
			}
			Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			
			//判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
			Integer num = allotCustomerService.getCustomerIsOverrun(eId, idArrayInt.length);
			if(num>0){
				return num+"";
			}else {
				allotCustomerService.batchAllotToEmployee(eId,idArrayInt,nowEmployee);
			}
			
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	@RequestMapping("getAllotToEmployeeList")
	public String getAllotToEmployeeList(Integer pageIndex,Integer pageSize,
			Model model,Integer initPage,String keyStr,Integer companyId,Integer departmentId,Integer table) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		List<Map<String, Object>> customers = null;
		Integer count = null;
		List<Employee> employees=null;
		Integer roleId = employee.getRole().getId();
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(1));
		}
		
		// 如果当前user的角色是（超级管理员、销售总监、总经理、市场部经理）就查询全部
		if (Constant.managerRoleIds.contains(roleId)) {		
			customers = allotCustomerService.getAllotToEmployeeList(null,pageIndex,pageSize,keyStr,companyId,departmentId);
			count = allotCustomerService.getAllotToEmployeeCount(null,keyStr,companyId,departmentId);

			List<Department> departments = departmentService.getDeparmentByName("业务");
			if (departments!=null) {
				Integer[] ids = new Integer[departments.size()];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = departments.get(i).getId();
				}
				employees= employeeService.getEmployeeByDIds(ids);
			}
		}else if((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))){
			customers = allotCustomerService.getAllotToEmployeeList(null,pageIndex,pageSize,keyStr,companyId,departmentId);
			count =  allotCustomerService.getAllotToEmployeeCount(null,keyStr,companyId,departmentId);
			if (departmentId!=null) {
				Integer[] ids = new Integer[]{departmentId};
				employees= employeeService.getEmployeeByDIds(ids);
			}
		}else{
			if (employee.getDepartment()!=null) {
				customers = allotCustomerService.getAllotToEmployeeList(employee.getDepartment().getId(),pageIndex,pageSize,keyStr,companyId,departmentId);
				count =  allotCustomerService.getAllotToEmployeeCount(employee.getDepartment().getId(),keyStr,companyId,departmentId);
				// 获取该部门的员工
				employees= employeeService.getEmployeeByDId(employee.getId(),employee.getDepartment().getId());
			}
		}
		companyId = (companyId==null?0:companyId);
		departmentId = (departmentId==null?0:departmentId);
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getAllotToEmployeeList.do?initPage=1&keyStr="+keyStr+"&companyId="+companyId+"&departmentId="+departmentId, "tableList");
		model.addAttribute("customers",customers);
		model.addAttribute("companyId",companyId);
		model.addAttribute("departmentId",departmentId);
		model.addAttribute("keyStr", keyStr);
		model.addAttribute("employees", employees);
		if (initPage==null) {
			return "page/customer/allotToEmployee";
		}else{
			if (table!=null && table==1) {
				return "page/customer/batchAllotToEmployee-List";
			}else {
				return "page/customer/allotToEmployee-List";
			}
		}
		
	}
	@RequestMapping("importBatchAllotAll")
	@ResponseBody
	public Boolean importBatchAllotAll(Integer companyId,Integer dId,Integer rId) throws Exception{
		List<Customer> customers = customerService.getListByAddType(rId,null);
		Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		try {
			allotCustomerService.batchAllotToDepartment(companyId,dId,customers,nowEmployee,rId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("importBatchAllot")
	@ResponseBody
	public Boolean importBatchAllot(Integer companyId,Integer dId,Integer rId,Integer count) throws Exception{
		List<Customer> customers = customerService.getListByAddType(rId,count);
		Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		try {
			allotCustomerService.batchAllotToDepartment(companyId,dId,customers,nowEmployee,rId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("batchAllotToEmplloy")
	@ResponseBody
	public String batchAllotToEmplloy(Integer count,Integer eId,Integer companyId,Integer departmentId) throws Exception{
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		List<Map<String, Object>> customers = null;
		Integer roleId = employee.getRole().getId();
		String type = employee.getType();
		// 如果当前user的角色是（超级管理员、销售总监、总经理、市场部经理）就查询全部
		if (Constant.managerRoleIds.contains(roleId)) {		
			customers = allotCustomerService.getAllotToEmployeeList(null,1,count,null,companyId,departmentId);
		}else if((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))){
			customers = allotCustomerService.getAllotToEmployeeList(null,1,count,null,companyId,departmentId);
		}else {
			customers = allotCustomerService.getAllotToEmployeeList(employee.getDepartment().getId(),1,count,null,companyId,departmentId);
		}
		Integer[] idArrayInt = new Integer[count];
		for (int i=0;i<customers.size();i++) {
			idArrayInt[i]=Integer.parseInt(customers.get(i).get("id").toString());
		}
		
		//判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
		Integer num = allotCustomerService.getCustomerIsOverrun(eId, idArrayInt.length);
		if(num>0){
			return num+"";
		}else {
			try {
				allotCustomerService.batchAllotToEmployee(eId,idArrayInt,employee);
			} catch (Exception e) {
				return "false";
			}
		}
		return "true";
	}
}
