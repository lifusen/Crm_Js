package cn.itproject.crm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.service.CenterService;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.util.Constant;
@Controller
@RequestMapping("/center")
public class CenterController extends BaseController{
	
	@Resource
	private CenterService centerService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private CompanyService companyService;
	
	/**
	 * 中心新订单
	 * @return
	 */
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
		// 获取权证部门
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
			List<Department> departmentList = departmentService.getDepartmentByCompanyId(employee.getCompanyId());
			model.addAttribute("departmentList",departmentList);
		}
		
		List<Map<String, Object>> list = centerService.orderList(query);
		Integer count = centerService.getOrderCount(query);
		model.addAttribute("customers", list);
		model.addAttribute("keyStr",query.getQueryWord());
		model.addAttribute("companyId",query.getCompanyId());
		model.addAttribute("dempId",query.getDempId());
		model.addAttribute("count",count);
		builderParam(model, query.getPageIndex(), query.getPageSize(), count, "center/orderList.do?time="+new Date().getTime(), "tableList");
		if (init) {
			return "page/centre/orderList";
		}else {
			return "page/centre/allotToEmployee-List";
		}
	}
}