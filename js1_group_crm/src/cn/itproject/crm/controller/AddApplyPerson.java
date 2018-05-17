package cn.itproject.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.ApplyPersonListService;
import cn.itproject.crm.service.ApplyPersonService;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

@Controller
@Scope("prototype")
@RequestMapping("/applyPerson")
public class AddApplyPerson extends BaseController {

	@Resource
	private ApplyPersonService applyPersonService;
	@Resource
	private ApplyPersonListService applyPersonListService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private CompanyService companyService;

	@RequestMapping("/addPersonInfo")
	public String addApplyPersionUI(Model model) throws Exception {
		return "page/apply/addApplyPersonInfo";
	}

	@RequestMapping("/addPerson")
	@ResponseBody
	public String addPersonOfHr(ApplyPerson applyPerson, Employee employee) {
		try {
			applyPersonService.addHrPerson(applyPerson, getLoginEmployee());
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/getValidPersonList")
	public String getValidCustomerList(Integer pageIndex, Integer pageSize, Model model, Integer initPage,
			String keyStr, Integer srarchDefault) throws Exception {
		pageSize = initPageSize(pageSize);
		pageIndex = initPageIndex(pageIndex);
		keyStr = (keyStr == null ? "" : keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> applyPerson = null;
		Integer count = null;
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(1));
		}
		if (roleId == 3) {
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(),
					employee.getDepartment().getId());
			if (employees != null && employees.size() > 0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = employees.get(i).getId();
				}
				applyPerson = applyPersonListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, keyStr);
				count = applyPersonListService.getCountByStateAndEId(Constant.validList, ids, keyStr);
			}
			getEmplloyByDids(model, new Integer[] { employee.getDepartment().getId() });
		} else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			applyPerson = applyPersonListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, keyStr);
			count = applyPersonListService.getCountByStateAndEId(Constant.validList, ids, keyStr);
		}
		builderParam(model, pageIndex, pageSize, count, "applyPerson/getValidPersonList.do?initPage=1&keyStr=" + keyStr,
				"tableList");
		System.out.println();
		model.addAttribute("roleId", roleId);
		model.addAttribute("customers", applyPerson);
		model.addAttribute("keyStr", keyStr);
		if (srarchDefault != null && srarchDefault == 1) {
			return "page/apply/table-body";
		}
		if (initPage == null) {
			return "page/apply/validCustomer";
		}
		return "page/apply/validCustomer-List";
	}

	private void getEmplloyByDids(Model model, Integer[] dIds) throws Exception {
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}

}
