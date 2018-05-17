package cn.itproject.crm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.WarrantDepartmentService;
import cn.itproject.crm.util.Constant;

@Controller
@RequestMapping("/warrantdepartment")
public class WarrantDepartmentController extends BaseController{

	@Resource
	private WarrantDepartmentService warrantDepartmentService;
	@Resource
	private DepartmentService departmentService;	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("/orderList")
	public String orderList(WarrantOrderQuery query,Model model) throws Exception{
		boolean init=false;
		if (query.getPageIndex()==null) {
			init = true;
			query.setPageIndex(1);
			query.setPageSize(10);
		}
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)) || Constant.warrantSuper.equals(employee.getRole().getId())) {
			model.addAttribute("companys", companyService.getAllCompany(2));
			if (query.getDempId()!=null && query.getDempId()>0) {
				Integer[] dids = new Integer[1];
				dids[0] = query.getDempId();
				List<Employee> employees = employeeService.getEmployeeByDIds(dids);
				model.addAttribute("employees", employees);
			}
		}else{
			Integer roleId = employee.getRole().getId();
			query.setCompanyId(employee.getCompanyId());
			query.setDempId(employee.getDepartment().getId());
			query.setEmpId(employee.getId());
			query.setRoleId(roleId);
			Integer dempId = employee.getDepartment().getId();
			Integer[] dids = new Integer[1];
			dids[0] = dempId;
			List<Employee> employees = employeeService.getEmployeeByDIds(dids);
			model.addAttribute("employees", employees);
		}
		List<Map<String, Object>> list = warrantDepartmentService.orderList(query);
		Integer count = warrantDepartmentService.getOrderCount(query);
		model.addAttribute("customers", list);
		model.addAttribute("keyStr",query.getQueryWord());
		model.addAttribute("count",count);
		model.addAttribute("companyId",query.getCompanyId());
		model.addAttribute("dempId", query.getDempId());
		builderParam(model, query.getPageIndex(), query.getPageSize(), count, "warrantdepartment/orderList.do?time="+new Date().getTime(), "tableList");
		if (init) {
			return "page/warrantdepartment/orderList";
		}else {
			return "page/warrantdepartment/allotToEmployee-List";
		}
	}
}
