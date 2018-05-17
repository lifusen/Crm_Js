package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.IntArraySerializer;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.AllotCustomerService;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.CustomerTurnService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.LendingInstitutionService;
import cn.itproject.crm.service.WarranterTurnService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

/**
 * 权证移交
 * @author SwordLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/warrant")
public class warranterTurnOverController extends BaseController{
	private static Logger logger = Logger.getLogger(warranterTurnOverController.class);
	
	@Resource
	private DepartmentService departmentService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private WarranterTurnService warranterTurnService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("/warranterTurnOverList.do")
	public String warranterTurnOverList(Integer pageIndex,Integer pageSize,Model model,Integer initPage,
			String keyStr,Integer departmentId,Integer employeeId,Integer companyId) throws Exception{
		//初始化关键字段
		departmentId = (departmentId==null?0:departmentId);
		companyId = (companyId==null?0:companyId);
		employeeId = (employeeId==null?0:employeeId);
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)) || Constant.warrantSuper.equals(roleId)) {
			model.addAttribute("companys", companyService.getCompanyByType(2));
		}else {
			model.addAttribute("roleId", employee.getRole().getId());
		}
		
		if (Constant.warrantSuper.equals(roleId) || Constant.warrantAdmin.equals(roleId) || 
				(StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
		//中心
			if (employeeId > 0) {
				Integer[] ids = new Integer[1];
				ids[0] = employeeId;
				customers = warranterTurnService.getTurnList(pageIndex, pageSize,ids,keyStr);
				count = warranterTurnService.getTurnCount(ids,keyStr);
			}else {
				if (departmentId > 0) {
					List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), 
							departmentId);
					if (employees!=null && employees.size()>0) {
						Integer[] ids = new Integer[employees.size()];
						for (int i=0;i<ids.length;i++) {
							ids[i] = employees.get(i).getId();
						}
						customers = warranterTurnService.getTurnList(pageIndex, pageSize,ids,keyStr);
						count = warranterTurnService.getTurnCount(ids,keyStr);
					}
				}else if (companyId>0) {
					List<Employee> employees = employeeService.getEmployeeByCompanyId(companyId);
					if (employees!=null && employees.size()>0) {
						List<Integer> idList = new ArrayList<>();
						for (int i=0;i<employees.size();i++) {
							idList.add(employees.get(i).getId());
						}
						Integer[] ids = new Integer[idList.size()];
						for(int i=0;i<ids.length;i++) {
							ids[i] = idList.get(i);
						}
						customers = warranterTurnService.getTurnList(pageIndex, pageSize,ids,keyStr);
						count = warranterTurnService.getTurnCount(ids,keyStr);
					}
				}else{
					customers = warranterTurnService.getTurnList(pageIndex, pageSize,null,keyStr);
					count = warranterTurnService.getTurnCount(null,keyStr);
				}
			}
		}else if(Constant.warrantManager.equals(roleId)){
		//部门
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), 
					employee.getDepartment().getId());
			model.addAttribute("employees", employees);
			if (employeeId > 0) {
				Integer[] ids = new Integer[1];
				ids[0] = employeeId;
				customers = warranterTurnService.getTurnList(pageIndex, pageSize,ids,keyStr);
				count = warranterTurnService.getTurnCount(ids,keyStr);
			}else {
				if (employees!=null && employees.size()>0) {
					Integer[] ids = new Integer[employees.size()];
					for (int i=0;i<ids.length;i++) {
						ids[i] = employees.get(i).getId();
					}
					customers = warranterTurnService.getTurnList(pageIndex, pageSize,ids,keyStr);
					count = warranterTurnService.getTurnCount(ids,keyStr);
				}
			}
		}else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = warranterTurnService.getTurnList(pageIndex, pageSize,ids,keyStr);
			count = warranterTurnService.getTurnCount(ids,keyStr);
		}
		List<Department> departments = null;
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)) || Constant.warrantSuper.equals(roleId)) {
			if (companyId!=null && companyId>0) {
				departments = departmentService.getDepartmentByCompanyId(companyId);
			}
		}else {
			if(Constant.warrantAdmin.equals(roleId)){
				departments = departmentService.getDepartmentByCompanyId(LoginUserUtil.getCompanyId());
			}else {
				departments = departmentService.getDeparmentByCompanyType(2);//权证公司类型: 2
			}
		}
		builderParam(model, pageIndex, pageSize, count, 
				"warrant/warranterTurnOverList.do?initPage=1&keyStr"+keyStr+"&dId="+
							departmentId+"&eId="+employeeId, "tableList");
		
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		setEmployeeCacheMap(model);
		
		model.addAttribute("customers", customers);
		model.addAttribute("companyId", companyId==null?0:companyId);
		model.addAttribute("keyStr", keyStr);
		model.addAttribute("departments", departments);
		model.addAttribute("dId", departmentId);
		model.addAttribute("eId", employeeId);
		model.addAttribute("roleId", roleId);
		if (initPage==null) {
			return "page/warrantTurnOver/warrantTurnOver";
		}
		return "page/warrantTurnOver/turnOver-List";
	}
	
	
	@RequestMapping("/doTurn.do")
	@ResponseBody
	public Boolean doTurn(String ids,Integer toEmployeeId,String cause,String otherCause,Integer companyId) throws Exception{
		String[] s_eIds = ids.split(",");
		Integer[] sIds = new Integer[s_eIds.length];
		Integer[] eIds = new Integer[s_eIds.length];
		for (int i = 0; i < s_eIds.length; i++) {
			String[] s_e = s_eIds[i].split("-");
			sIds[i] = Integer.parseInt(s_e[0]);
			eIds[i] = Integer.parseInt(s_e[1]);
		}
		warranterTurnService.doTurn(sIds,eIds,toEmployeeId,cause,otherCause);
		return true;
	}
	
	@RequestMapping("/getTurnCount.do")
	@ResponseBody
	public Integer getTurnCount(String keyStr,Integer dId,Integer eId) throws Exception{
		dId = (dId==null?0:dId);
		eId = (eId==null?0:eId);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		Integer count = null;
		if (Constant.warrantSuper == roleId || Constant.warrantAdmin == roleId) {
			if (eId>0) {
				Integer[] ids = new Integer[1];
				ids[0] = eId;
				count = warranterTurnService.getTurnCount(ids,keyStr);
			}else {
				if (dId>0) {
					List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), dId);
					if (employees!=null && employees.size()>0) {
						Integer[] ids = new Integer[employees.size()];
						for (int i=0;i<ids.length;i++) {
							ids[i] = employees.get(i).getId();
						}
						count = warranterTurnService.getTurnCount(ids,keyStr);
					}
				}else{
					count = warranterTurnService.getTurnCount(null,keyStr);
				}
			}
		}else if(roleId==Constant.warrantManager){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
			if (employees!=null && employees.size()>0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i=0;i<ids.length;i++) {
					ids[i] = employees.get(i).getId();
				}
				count = warranterTurnService.getTurnCount(ids,keyStr);
			}
		}else {
			if (eId>0) {
				Integer[] ids = new Integer[1];
				ids[0] = eId;
				count = warranterTurnService.getTurnCount(ids,keyStr);
			}else {
				if (dId>0) {
					List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), dId);
					if (employees!=null && employees.size()>0) {
						Integer[] ids = new Integer[employees.size()];
						for (int i=0;i<ids.length;i++) {
							ids[i] = employees.get(i).getId();
						}
						count = warranterTurnService.getTurnCount(ids,keyStr);
					}
				}else{
					count = warranterTurnService.getTurnCount(null,keyStr);
				}
			}
		}
		return count;
	}
	
	
	@RequestMapping("/batchTurn.do")
	@ResponseBody
	public Boolean batchTurn(String keyStr,Integer dId,Integer eId,Integer toEmployeeId,String cause,String otherCause){
		try {
			dId = (dId==null?0:dId);
			eId = (eId==null?0:eId);
			keyStr = (keyStr==null?"":keyStr);
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			Integer roleId = employee.getRole().getId();
			List<Integer> sIds = null;
			if (Constant.warrantAdmin == roleId || Constant.warrantSuper == roleId) {
				if (eId>0) {
					Integer[] ids = new Integer[1];
					ids[0] = eId;
					sIds = warranterTurnService.getTurnIds(ids,keyStr);
				}else {
					if (dId>0) {
						List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), dId);
						if (employees!=null && employees.size()>0) {
							Integer[] ids = new Integer[employees.size()];
							for (int i=0;i<ids.length;i++) {
								ids[i] = employees.get(i).getId();
							}
							sIds = warranterTurnService.getTurnIds(ids,keyStr);
						}
					}else{
						sIds = warranterTurnService.getTurnIds(null,keyStr);
					}
				}
			}else if(roleId==Constant.warrantManager){
				List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
				if (employees!=null && employees.size()>0) {
					Integer[] ids = new Integer[employees.size()];
					for (int i=0;i<ids.length;i++) {
						ids[i] = employees.get(i).getId();
					}
					sIds = warranterTurnService.getTurnIds(ids,keyStr);
				}
			}else {
				Integer[] ids = new Integer[1];
				ids[0] = employee.getId();
				sIds = warranterTurnService.getTurnIds(ids,keyStr);
			}
			warranterTurnService.batchTurn(sIds,eId,toEmployeeId,cause,otherCause);
		} catch (Exception e) {
			logger.error("turn error",e);
			return false;
		}
		return true;
	}
}
