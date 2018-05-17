package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.FollowSignInfoVo;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerListService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.NotificationService;
import cn.itproject.crm.util.Constant;

/**
 * 客户跟进控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customerFollow")
public class CustomerFollowController extends BaseController{
	
	@Resource
	private CustomerFollowService customerFollowService;
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CustomerListService customerListService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private NotificationService notificationService;
	
	@RequestMapping("/getList")
	public String getList(Integer customerId,Integer pageIndex,Integer pageSize,Model model) throws Exception{
		System.out.println("customerId:"+customerId);
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		
		
		List<CustomerFollow> customerFollows = customerFollowService.getListByCustomerId(customerId, pageIndex, pageSize);
		model.addAttribute("customerFollows", customerFollows);
		Integer count = customerFollowService.getCountByCustomerId(customerId);
		System.out.println(count);
		builderParam(model, pageIndex, pageSize, count, 
				"customerFollow/getList.do?initPage=1&customerId="+customerId, "customerFollowListArea");
		return "page/customerFollow/list";
	}
	
	
	@RequestMapping("/addCustomerFollow")
	@ResponseBody
	public Map<String, Object> addCustomerFollow(CustomerFollow customerFollow,FollowSignInfoVo followSignInfoVo) throws Exception {
		System.out.println("addCustomerFollow..."+customerFollow);
		System.out.println("followSignInfoVo..."+followSignInfoVo);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer visibility = customerFollow.getVisibility();
			if (visibility!=null&&visibility==0) {
				Boolean flag = customerFollowService.isCanAddVisibility(getLoginEmployee().getId());
				System.out.println(flag);
				if (!flag) {
					map.put("flag", false);
					map.put("msg", "自留客户最多可以设置50个客户!");
					return map;
				}
			}
			customerFollowService.addCustomerFollow(customerFollow, getLoginEmployee(), followSignInfoVo.getFollowSignCustomerId());
			
			Integer senderId = getLoginEmployee().getId();								// 发送人
			
			Boolean warrantRole = (Boolean) getSession(Constant.WARRANT_ROLE);
			// 发送通知
			if (warrantRole == true) { // 发送通知给业务人员
				notificationService.addNotificationToSalesman(senderId, followSignInfoVo);
			}else{ // 发送通知给权证人员
				notificationService.addNotification(senderId, followSignInfoVo);
			}
			
		
			
			map.put("flag", true);
			map.put("msg", "添加成功!");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("flag", false);
		map.put("msg", "添加失败!");
		return map;
	}
	
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
			getEmplloyByDids(model,new Integer[]{loginEmployee.getDepartment().getId()});
			if (eId>0) {
				employeeIds.add(eId);
			}else {
				//获取该部门下的所有员工
				employeeIds = employeeService.getEmployeeIdsByDId(loginEmployee.getDepartment().getId());
			}
		}else {											//业务员
			roleType = 2;
			employeeIds.add(loginEmployee.getId());
		}
		Integer[] es = null;
		if (employeeIds!=null && employeeIds.size()>0) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		//获取列表
		customerReminders = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, es, Constant.reminderList, keyword,beginDate,endDate, "getCustomerReminderListByEmployeeIds",null,fllowType);
		//获取总行数
		count = customerListService.getCountByStateAndEId(Constant.reminderList, es, keyword,beginDate,endDate, "getCustomerReminderListCountByEmployeeIds",null,fllowType);
		System.out.println("customerReminders==============>");
		System.out.println(count);
		for (Map<String, Object> map : customerReminders) {
			System.out.println(map);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"customerFollow/getReminderList.do?initPage=1&keyword="+keyword
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
			return "page/customerFollow/reminderListUI";
		}
		return "page/customerFollow/reminderList";
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
