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
import cn.itproject.crm.util.Constant;

/**
 * 客户移交
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class CustomerTurnOverController extends BaseController{
	private static Logger logger = Logger.getLogger(CustomerTurnOverController.class);
	
	@Resource
	private CustomerService customerService;
	@Resource
	private ContactsService contactsService;
	@Resource
	private CustomerFollowService customerFollowService;
	@Resource
	private LendingInstitutionService lendingInstitutionService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private AllotCustomerService allotCustomerService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CustomerTurnService customerTurnService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("turnOverList")
	public String turnOverList(Integer pageIndex,Integer pageSize,Model model,Integer initPage,
			String keyStr,Integer dId,Integer eId,String cLevel,String fLevel,Integer companyId) throws Exception{
		if ("".equals(cLevel)) {
			cLevel = null;
		}
		if ("".equals(fLevel)) {
			fLevel = null;
		}
		dId = (dId==null?0:dId);
		companyId = (companyId==null?0:companyId);
		eId = (eId==null?0:eId);
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getCompanyByType(1));
		}else {
			model.addAttribute("roleId", employee.getRole().getId());
		}
		
		if (Constant.managerRoleIds.contains(roleId) || 
				(StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			if (eId>0) {
				Integer[] ids = new Integer[1];
				ids[0] = eId;
				customers = customerTurnService.getTurnList(pageIndex, pageSize,ids,keyStr,fLevel,cLevel);
				count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
			}else {
				if (dId>0) {
					List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), 
							dId);
					if (employees!=null && employees.size()>0) {
						Integer[] ids = new Integer[employees.size()];
						for (int i=0;i<ids.length;i++) {
							ids[i] = employees.get(i).getId();
						}
						customers = customerTurnService.getTurnList(pageIndex, pageSize,ids,keyStr,fLevel,cLevel);
						count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
					}
				}else if (companyId>0) {
					List<Employee> employees = employeeService.getEmployeeByCompanyId(companyId);
					if (employees!=null && employees.size()>0) {
						List<Integer> idList = new ArrayList<>();
						for (int i=0;i<employees.size();i++) {
							if (employees.get(i).getDepartment()!=null) {
								if ("业务".equals(employees.get(i).getDepartment().getType())) {
									idList.add(employees.get(i).getId());
//									ids[i] = employees.get(i).getId();
								}
							}
						}
						Integer[] ids = new Integer[idList.size()];
						for(int i=0;i<ids.length;i++) {
							ids[i] = idList.get(i);
						}
						customers = customerTurnService.getTurnList(pageIndex, pageSize,ids,keyStr,fLevel,cLevel);
						count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
					}
				}else{
					customers = customerTurnService.getTurnList(pageIndex, pageSize,null,keyStr,fLevel,cLevel);
					count = customerTurnService.getTurnCount(null,keyStr,fLevel,cLevel);
				}
			}
		}else if(roleId==3){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), 
					employee.getDepartment().getId());
			model.addAttribute("employees", employees);
			if (eId>0) {
				Integer[] ids = new Integer[1];
				ids[0] = eId;
				customers = customerTurnService.getTurnList(pageIndex, pageSize,ids,keyStr,fLevel,cLevel);
				count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
			}else {
				if (employees!=null && employees.size()>0) {
					Integer[] ids = new Integer[employees.size()];
					for (int i=0;i<ids.length;i++) {
						ids[i] = employees.get(i).getId();
					}
					customers = customerTurnService.getTurnList(pageIndex, pageSize,ids,keyStr,fLevel,cLevel);
					count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
				}
			}
		}else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = customerTurnService.getTurnList(pageIndex, pageSize,ids,keyStr,fLevel,cLevel);
			count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
		}
		List<Department> departments = null;
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			if (companyId!=null) {
				Integer[] companyIds = new Integer[]{companyId};
				departments = departmentService.getDepartmentByCompanyIds(companyIds, "业务");
			}
		}else {
			departments = departmentService.getDeparmentByName("业务");
		}
		builderParam(model, pageIndex, pageSize, count, 
				"customer/turnOverList.do?initPage=1&keyStr"+keyStr+"&dId="+
							dId+"&eId="+eId+"&fLevel="+(fLevel==null?"":fLevel)+"&cLevel="+(cLevel==null?"":cLevel), "tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("companyId", companyId==null?0:companyId);
		model.addAttribute("keyStr", keyStr);
		model.addAttribute("departments", departments);
		model.addAttribute("dId", dId);
		model.addAttribute("eId", eId);
		model.addAttribute("fLevel", fLevel);
		model.addAttribute("cLevel", cLevel);
		if (initPage==null) {
			return "page/customer/customerTurnOver";
		}
		return "page/customer/turnOver-List";
	}
	
	
	@RequestMapping("doTurn")
	@ResponseBody
	public String doTurn(String ids,Integer toEmployeeId,String cause,String otherCause,Integer companyId) throws Exception{
		String[] c_eIds = ids.split(",");
		Integer[] cIds = new Integer[c_eIds.length];
		Integer[] eIds = new Integer[c_eIds.length];
		for (int i = 0; i < c_eIds.length; i++) {
			String[] c_e = c_eIds[i].split("-");
			cIds[i] = Integer.parseInt(c_e[0]);
			eIds[i] = Integer.parseInt(c_e[1]);
		}
		
		//判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
		Integer num = allotCustomerService.getCustomerIsOverrun(toEmployeeId, cIds.length);
		if(num>0){
			return num+""; 
		}else {
			try {
				customerTurnService.doTurn(cIds,eIds,toEmployeeId,cause,otherCause);
			} catch (Exception e) {
				e.printStackTrace();
				return "false";
			}
		}
		return "true";
	}
	
	@RequestMapping("getTurnCount")
	@ResponseBody
	public Integer getTurnCount(String keyStr,Integer dId,Integer eId,String cLevel,String fLevel) throws Exception{
		dId = (dId==null?0:dId);
		eId = (eId==null?0:eId);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		Integer count = null;
		if (Constant.managerRoleIds.contains(roleId)) {
			if (eId>0) {
				Integer[] ids = new Integer[1];
				ids[0] = eId;
				count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
			}else {
				if (dId>0) {
					List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), dId);
					if (employees!=null && employees.size()>0) {
						Integer[] ids = new Integer[employees.size()];
						for (int i=0;i<ids.length;i++) {
							ids[i] = employees.get(i).getId();
						}
						count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
					}
				}else{
					count = customerTurnService.getTurnCount(null,keyStr,fLevel,cLevel);
				}
			}
		}else if(roleId==3){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
			if (employees!=null && employees.size()>0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i=0;i<ids.length;i++) {
					ids[i] = employees.get(i).getId();
				}
				count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
			}
		}else {
			if (eId>0) {
				Integer[] ids = new Integer[1];
				ids[0] = eId;
				count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
			}else {
				if (dId>0) {
					List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), dId);
					if (employees!=null && employees.size()>0) {
						Integer[] ids = new Integer[employees.size()];
						for (int i=0;i<ids.length;i++) {
							ids[i] = employees.get(i).getId();
						}
						count = customerTurnService.getTurnCount(ids,keyStr,fLevel,cLevel);
					}
				}else{
					count = customerTurnService.getTurnCount(null,keyStr,fLevel,cLevel);
				}
			}
		}
		return count;
	}
	
	
	@RequestMapping("batchTurn")
	@ResponseBody
	public String batchTurn(String keyStr,Integer dId,Integer eId,String cLevel,
			String fLevel,Integer toEmployeeId,String cause,String otherCause){
		try {
			dId = (dId==null?0:dId);
			eId = (eId==null?0:eId);
			keyStr = (keyStr==null?"":keyStr);
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			Integer roleId = employee.getRole().getId();
			List<Integer> cIds = null;
			if (Constant.managerRoleIds.contains(roleId)) {
				if (eId>0) {
					Integer[] ids = new Integer[1];
					ids[0] = eId;
					cIds = customerTurnService.getTurnIds(ids,keyStr,fLevel,cLevel);
				}else {
					if (dId>0) {
						List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), dId);
						if (employees!=null && employees.size()>0) {
							Integer[] ids = new Integer[employees.size()];
							for (int i=0;i<ids.length;i++) {
								ids[i] = employees.get(i).getId();
							}
							cIds = customerTurnService.getTurnIds(ids,keyStr,fLevel,cLevel);
						}
					}else{
						cIds = customerTurnService.getTurnIds(null,keyStr,fLevel,cLevel);
					}
				}
			}else if(roleId==3){
				List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
				if (employees!=null && employees.size()>0) {
					Integer[] ids = new Integer[employees.size()];
					for (int i=0;i<ids.length;i++) {
						ids[i] = employees.get(i).getId();
					}
					cIds = customerTurnService.getTurnIds(ids,keyStr,fLevel,cLevel);
				}
			}else {
				Integer[] ids = new Integer[1];
				//ids[0] = employee.getId();  问题严重~！这是将操作者的客户移交了出去！ 2017.01.23 by SwordLiu
				ids[0] = eId;
				cIds = customerTurnService.getTurnIds(ids,keyStr,fLevel,cLevel);
			}
			
			//判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
			Integer num = allotCustomerService.getCustomerIsOverrun(toEmployeeId, cIds.size());
			if(num>0){
				return num+"";
			}else {
				customerTurnService.batchTurn(cIds,eId,toEmployeeId,cause,otherCause);
			}
			
		} catch (Exception e) {
			logger.error("turn error",e);
			return "false";
		}
		return "true";
	}
}
