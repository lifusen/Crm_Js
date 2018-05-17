package cn.itproject.crm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.ShowCustomer;
import cn.itproject.crm.service.AllotCustomerService;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerListService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

/**
 * 已分配订单
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class AllotOrderCustomerController extends BaseController{
	
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
	private CustomerListService customerListService;
	
	@RequestMapping("getAssignedOrderList")
	public String getAssignedOrderList(Integer pageIndex,Integer pageSize,
			Model model,Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		if (Constant.managerRoleIds.contains(roleId)) {
			customers = allotCustomerService.getAssignedOrderList(pageIndex,pageSize,null,keyStr);
			count = allotCustomerService.getAssignedCount(null,keyStr);
		}else {
			customers = allotCustomerService.getAssignedOrderList(pageIndex,pageSize,employee.getId(),keyStr);
			count = allotCustomerService.getAssignedCount(employee.getId(),keyStr);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getAssignedOrderList.do?initPage=1&keyStr="+keyStr, "tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("keyStr", keyStr);
		
		List<Department> departments = departmentService.getDeparmentByName("业务");
		Integer[] ids = new Integer[departments.size()];
		int i=0;
		for (Department d:departments) {
			ids[i] = d.getId();
			i++;
		}
		// 获取业务部的所有员工
		List<Employee> employees = employeeService.getEmployeeByDIds(ids);
		model.addAttribute("employees",employees);
		model.addAttribute("department",departments);
		if (initPage==null) {
			return "page/customer/allotOrder";
		}
		return "page/customer/allotOrder-List";
	}
	
	@RequestMapping("getReceiveOrderList")
	public String getReceiveOrderList(Integer pageIndex,Integer pageSize,Model model,
			Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		if (Constant.managerRoleIds.contains(roleId)) {
			customers = allotCustomerService.getReceiveOrderList(pageIndex, pageSize,null,keyStr);
			count = allotCustomerService.getReceiveOrderCount(null,keyStr);
		}else if(roleId==3){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
			if (employees!=null && employees.size()>0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i=0;i<ids.length;i++) {
					ids[i] = employees.get(i).getId();
				}
				customers = allotCustomerService.getReceiveOrderList(pageIndex, pageSize,ids,keyStr);
				count = allotCustomerService.getReceiveOrderCount(ids,keyStr);
			}
		}else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = allotCustomerService.getReceiveOrderList(pageIndex, pageSize,ids,keyStr);
			count = allotCustomerService.getReceiveOrderCount(ids,keyStr);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getReceiveOrderList.do?initPage=1&keyStr="+keyStr, "tableList");
		model.addAttribute("customers", customers);
		List<Department> departments = departmentService.getDeparmentByName("业务");
		Integer[] ids = new Integer[departments.size()];
		int i=0;
		for (Department d:departments) {
			ids[i] = d.getId();
			i++;
		}
		// 获取业务部的所有员工
		List<Employee> employees = employeeService.getEmployeeByDIds(ids);
		model.addAttribute("keyStr",keyStr);
		model.addAttribute("department",departments);
		model.addAttribute("employees",employees);
		if (initPage==null) {
			return "page/customer/receiveOrder";
		}
		return "page/customer/receiveOrder-List";
	}
	
	@RequestMapping("searchReceiveOrderList")
	public String searchReceiveOrderList(Integer pageIndex,Model model,
			Integer departmentId,Integer employeeId) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		List<ShowCustomer> customers = customerService.getReceiveOrderList(pageIndex, pageSize,departmentId,employeeId);
		Integer count = customerService.getReceiveOrderCount(departmentId,employeeId);
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getReceiveOrderList.do?initPage=1&"
				+ "departmentId="+departmentId+"&employeeId="+employeeId, "tableList");
		model.addAttribute("customers", customers);
		List<Department> departments = departmentService.getDeparmentByName("业务");
		Integer[] ids = new Integer[departments.size()];
		int i=0;
		for (Department d:departments) {
			ids[i] = d.getId();
			i++;
		}
		// 获取业务部的所有员工
		List<Employee> employees = employeeService.getEmployeeByDIds(ids);
		model.addAttribute("department",departments);
		model.addAttribute("employees",employees);
		return "page/customer/receiveOrder-List";
	}
	
	@RequestMapping("receive")
	@ResponseBody
	public Map<String, Object> receive(Integer id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		
		Integer roleId = employee.getRole().getId();
		if (!Constant.managerRoleIds.contains(roleId)) {
			// 查询当天释放了多少
			Integer count = customerService.getReceiveById(employee.getId(),new Date());
			if (count>=10) {	// 一天只能释放10个
				map.put("flag", false);
				map.put("msg","一天只能领取10个");
				return map;
			}
		}
		Customer customer = customerService.getEntity(id);
		Integer count = customerService.getCustomerByPhoneAndState(customer.getPhone(),Constant.validList);
		if(count>0){
			map.put("flag", false);
			map.put("msg", "该客户电话号码和有效客户列表重复，无法领取");
			return map;
		}
		
		//判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
		Integer num = allotCustomerService.getCustomerIsOverrun(employee.getId(), 1);
		if(num>0){
			map.put("msg", "你所持有的客户数量已达上限，不能继续领取！");
			map.put("flag", false);
			return map;
		}else {
			customerService.receive(id,employee);
		}
		map.put("flag", true);
		return map;
	}
}
