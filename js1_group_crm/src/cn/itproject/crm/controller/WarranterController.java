package cn.itproject.crm.controller;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.WarrantDepartmentService;
import cn.itproject.crm.service.WarranterService;
import cn.itproject.crm.util.Constant;


@Controller
@RequestMapping("/warranter")
public class WarranterController extends BaseController{
	@Resource
	private WarranterService warranterService;
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
		setEmployeeCacheMap(model);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)) || Constant.warrantSuper.equals(employee.getRole().getId())) {
			model.addAttribute("companys", companyService.getAllCompany(2));
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
		setQuery(model,query);
		
		List<Map<String, Object>> list = warranterService.orderList(query);
		Integer count = warranterService.getOrderCount(query);
		model.addAttribute("customers", list);
		model.addAttribute("roleId", employee.getRole().getId());
		model.addAttribute("keyStr",query.getQueryWord());
		model.addAttribute("count",count);
		builderParam(model, query.getPageIndex(), query.getPageSize(), count, "warranter/orderList.do?time="+new Date().getTime(), "tableList");
		if (init) {
			return "page/warranter/orderList";
		}else {
			return "page/warranter/customer-List";
		}
	}
	
	private void setQuery(Model model,WarrantOrderQuery queryvo) throws Exception{
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)) || Constant.warrantSuper.equals(employee.getRole().getId())) {
			// 查询出所有的权证中心
			List<Company> list = companyService.getCompanyByType(2);
			model.addAttribute("queryCompanys", list);
		}else if (queryvo.getRoleId().intValue()==Constant.warrantAdmin) { 		// 权证中心管理员
			// 查询出当前中心的所有部门
			List<Department> list = departmentService.getByCompanyId(queryvo.getCompanyId());
			model.addAttribute("queryDepartments", list);
		}else if (queryvo.getRoleId().intValue()==Constant.warrantManager) { 	// 权证部经理
			Integer[] ids = new Integer[]{queryvo.getDempId()};
			List<Employee> list = employeeService.getEmployeeByDIds(ids);
			model.addAttribute("queryEmployees", list);
		}
	}
	
	
	@RequestMapping("/searchList")
	public String searchList(String companyIds,String depIds,String empIds,String types,
			String loanTypes,Date signDateStart,Date signDateEnd,Integer pageIndex,Integer pageSize,Model model) throws Exception{
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		
		if (pageIndex==null) {
			pageIndex = 1;
			pageSize = 10;
		}
		Integer[] companyIdArray = null;
		Integer[] depIdArray = null;
		Integer[] empIdArray = null;
		Integer[] typeArray = null;
		String[] loanTypeArray = null;
		if (companyIds!=null && !companyIds.equals("")) {
			String[] companyId = companyIds.split(",");
			companyIdArray = new Integer[companyId.length];
			for(int i=0;i<companyId.length;i++){
				companyIdArray[i] = Integer.parseInt(companyId[i]);
			}
		}
		if (depIds!=null && !depIds.equals("")) {
			String[] depId = depIds.split(",");
			depIdArray = new Integer[depId.length];
			for(int i=0;i<depId.length;i++){
				depIdArray[i] = Integer.parseInt(depId[i]);
			}
		}
		if (empIds!=null && !empIds.equals("")) {
			String[] empId = empIds.split(",");
			empIdArray = new Integer[empId.length];
			for(int i=0;i<empId.length;i++){
				empIdArray[i] = Integer.parseInt(empId[i]);
			}
		}
		if (types!=null && !types.equals("")) {
			String[] typeId = types.split(",");
			typeArray = new Integer[typeId.length];
			for(int i=0;i<typeId.length;i++){
				typeArray[i] = Integer.parseInt(typeId[i]);
			}
		}
		if (loanTypes!=null && !loanTypes.equals("")) {
			loanTypeArray = loanTypes.split(",");
		}
		List<Map<String, Object>> list = warranterService.search(companyIdArray,depIdArray,empIdArray,typeArray,loanTypeArray,signDateStart,signDateEnd,pageIndex,pageSize);		
		Integer count = warranterService.searchCount(companyIdArray,depIdArray,empIdArray,typeArray,loanTypeArray,signDateStart,signDateEnd);
		model.addAttribute("customers", list);
		model.addAttribute("count",count);
		builderParam(model, pageIndex, pageSize, count, "warranter/searchList.do?time="+new Date().getTime(), "tableList1");
		return "page/warranter/table-list";
	}
}
