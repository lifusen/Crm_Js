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

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerListService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;


/**
 * 无效客户
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class NullityCustomerController extends BaseController{
	
	@Resource
	private CustomerService customerService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	CustomerListService customerListService;
	@Resource
	CustomerFollowService customerFollowService;
	
	@RequestMapping("applyNullity")
	@ResponseBody
	public Map<String, Object> applyNullity(Integer cId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/**
			Integer count = customerFollowService.getCountByCustomerId(cId);
			if (count>1) {
				map.put("flag", false);
				map.put("msg","跟进过的客户不能申请无效");
				return map;
			}
			**/
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			Integer roleId = employee.getRole().getId();
			if (!Constant.managerRoleIds.contains(roleId)) {
				// 查询当天释放了多少
				Integer count = customerService.getReleaseById(employee.getId(),new Date());
				if (count>=10) {	// 一天只能释放10个
					map.put("flag", false);
					map.put("msg","一天只能申请无效/释放公共池10个");
					return map;
				}
			}
			customerService.applyNullity(cId);
			map.put("flag", true);
			return map;
		} catch (Exception e) {
			System.out.println(e);
			map.put("flag", false);
			map.put("msg","申请失败");
			return map;
		}
	}
	@RequestMapping("verify")
	@ResponseBody
	public Boolean verify(String ids,Integer state) throws Exception{
		String[] c_eIds = ids.split(",");
		Integer[] cIds = new Integer[c_eIds.length];
		for (int i = 0; i < c_eIds.length; i++) {
			cIds[i] = Integer.parseInt(c_eIds[i]);
		}
		customerService.verify(cIds,state);
		return true;
	}
	
	@RequestMapping("releasePublic")
	@ResponseBody
	public Map<String, Object> releasePublic(Integer cId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/**
			Integer count = customerFollowService.getCountByCustomerId(cId);
			if (count>1) {
				map.put("flag", false);
				map.put("msg","跟进过的客户不能释放");
				return map;
			}
			**/
			
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			Integer roleId = employee.getRole().getId();
			if (!Constant.managerRoleIds.contains(roleId)) {
				// 查询当天释放了多少
				Integer count = customerService.getReleaseById(employee.getId(),new Date());
				if (count>=10) {	// 一天只能释放10个
					map.put("flag", false);
					map.put("msg","一天只能申请无效/释放公共池10个");
					return map;
				}
			}
			customerService.releasePublic(cId);
			map.put("flag", true);
			return map;
		} catch (Exception e) {
			System.out.println(e);
			map.put("flag", false);
			map.put("msg","释放失败");
			return map;
		}
	}
	
	
	@RequestMapping("getApplyNullityList")
	public String getApplyNullityList(Integer pageIndex,Integer pageSize,Model model,Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		if (Constant.managerRoleIds.contains(roleId)) {
			customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, null, Constant.auditList, keyStr);
			count = customerListService.getCountByStateAndEId(Constant.auditList, null, keyStr);
		}else if(roleId==3){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
			if (employees!=null && employees.size()>0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i=0;i<ids.length;i++) {
					ids[i] = employees.get(i).getId();
				}
				customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, Constant.auditList, keyStr);
				count = customerListService.getCountByStateAndEId(Constant.auditList, ids, keyStr);
			}
		}else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, Constant.auditList, keyStr);
			count = customerListService.getCountByStateAndEId(Constant.auditList, ids, keyStr);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getApplyNullityList.do?initPage=1&keyStr="+keyStr, "tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("keyStr", keyStr);
		if (initPage==null) {
			return "page/customer/applyNullity";
		}
		return "page/customer/applyNullity-List";
	}

	@RequestMapping("getNullityList")
	public String getNullityList(Integer pageIndex,Integer pageSize,Model model,Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		if (Constant.managerRoleIds.contains(roleId)) {
			customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, null, Constant.invalidList, keyStr);
			count = customerListService.getCountByStateAndEId(Constant.invalidList, null, keyStr);
		}else if(roleId==3){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
			if (employees!=null && employees.size()>0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i=0;i<ids.length;i++) {
					ids[i] = employees.get(i).getId();
				}
				customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, Constant.invalidList, keyStr);
				count = customerListService.getCountByStateAndEId(Constant.invalidList, ids, keyStr);
			}
		}else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, Constant.invalidList, keyStr);
			count = customerListService.getCountByStateAndEId(Constant.invalidList, ids, keyStr);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getNullityList.do?initPage=1&keyStr"+keyStr, "tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("keyStr", keyStr);
		if (initPage==null) {
			return "page/customer/nullity";
		}
		return "page/customer/nullity-List";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Boolean delete(Integer id){
		try {
			customerService.deleteCustomer(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@RequestMapping("getCommonPoolList")
	public String getCommonPoolList(Integer pageIndex,Integer pageSize,Model model,Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		List<Map<String, Object>> customers = null;
		Integer count = null;
		customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, null, Constant.commonPoolList, keyStr);
		count = customerListService.getCountByStateAndEId(Constant.commonPoolList, null, keyStr);
		
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getCommonPoolList.do?initPage=1&keyStr="+keyStr, "tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("keyStr", keyStr);
		if (initPage==null) {
			return "page/customer/commonPool";
		}
		return "page/customer/commonPool-List";
	}
}
