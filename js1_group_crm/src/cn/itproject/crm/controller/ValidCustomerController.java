package cn.itproject.crm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.CustomerListService;
import cn.itproject.crm.service.CustomerSourceService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

/**
 * 有效客户列表
 * 
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class ValidCustomerController extends BaseController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private CustomerListService customerListService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private CustomerSourceService customerSourceService;
	@Resource
	private CompanyService companyService;

	@RequestMapping("getValidCustomerList")
	public String getValidCustomerList(Integer pageIndex, Integer pageSize, Model model, Integer initPage,
			String keyStr, Integer srarchDefault) throws Exception {
		pageSize = initPageSize(pageSize);
		pageIndex = initPageIndex(pageIndex);
		keyStr = (keyStr == null ? "" : keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(1));
		}
		if (Constant.managerRoleIds.contains(roleId)) { // 角色为管理层
			customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, null, Constant.validList,
					keyStr);
			count = customerListService.getCountByStateAndEId(Constant.validList, null, keyStr);
			getDepartmentAndEmplloy(model);
		} else if (roleId == 3) {
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(),
					employee.getDepartment().getId());
			if (employees != null && employees.size() > 0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = employees.get(i).getId();
				}
				customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, Constant.validList,
						keyStr);
				count = customerListService.getCountByStateAndEId(Constant.validList, ids, keyStr);
			}
			getEmplloyByDids(model, new Integer[] { employee.getDepartment().getId() });
		} else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = customerListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, Constant.validList,
					keyStr);
			count = customerListService.getCountByStateAndEId(Constant.validList, ids, keyStr);
		}
		builderParam(model, pageIndex, pageSize, count, "customer/getValidCustomerList.do?initPage=1&keyStr=" + keyStr,
				"tableList");

		model.addAttribute("roleId", roleId);
		model.addAttribute("customers", customers);
		model.addAttribute("keyStr", keyStr);
		// 客户来源
		model.addAttribute("sources", customerSourceService.getCustomerSourceList());
		if (srarchDefault != null && srarchDefault == 1) {
			return "page/customer/table-body";
		}
		if (initPage == null) {
			return "page/customer/validCustomer";
		}
		return "page/customer/validCustomer-List";
	}

	private void getDepartmentAndEmplloy(Model model) throws Exception {
		List<Department> departments = departmentService.getDeparmentByName("业务");
		Integer[] ids = new Integer[departments.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = departments.get(i).getId();
		}
		getEmplloyByDids(model, ids);
		model.addAttribute("departments", departments);
	}

	private void getEmplloyByDids(Model model, Integer[] dIds) throws Exception {
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}

	/**
	 * 高级查询
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param model
	 * @param depaIds
	 *            部门id
	 * @param emplIds
	 *            员工id
	 * @param loanIds
	 *            贷款类型
	 * @param clsIds
	 *            客户等级
	 * @param cstasIds
	 *            客户状态
	 * @param csourcesIds客户来源
	 * @param beginDate
	 *            录入时间开始
	 * @param endDate
	 *            录入时间结束
	 * @param orderKey
	 *            排序字段
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchValidCustomer")
	public String searchValidCustomer(Integer pageIndex, Integer pageSize, Model model, String companyIds,
			String depaIds, String emplIds, String loanIds, String clsIds, String cstasIds, String csourcesIds,
			String beginDate, String endDate, String orderKey, String follows, String wages, String units,
			String visibilitys) throws Exception {
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		String type = employee.getType();

		// 部门id
		Integer[] dids = null;
		if (Constant.managerRoleIds.contains(roleId)
				|| (StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			// 部门id
			dids = getIds(depaIds);
		} else {
			Integer dId = employee.getDepartment().getId();
			dids = new Integer[1];
			dids[0] = dId;
		}
		// 员工id
		Integer[] eids = null;
		if (!Constant.managerRoleIds.contains(roleId) && Constant.businessManagerRoleId != roleId
				&& !((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)))) {
			eids = new Integer[1];
			eids[0] = employee.getId();
		} else {
			eids = getIds(emplIds);
		}
		// 状态id
		Integer[] statesIds = getIds(cstasIds);
		if (statesIds == null) {
			statesIds = new Integer[4];
			statesIds[0] = 2;
			statesIds[1] = 3;
			statesIds[2] = 8;
			statesIds[3] = 4;
			statesIds = (Integer[]) Constant.validList.toArray(new Integer[Constant.validList.size()]);
		}
		// 来源id
		Integer[] sIds = getIds(csourcesIds);
		// 客户等级
		String[] customerL = getStrArrayl(clsIds);
		// 贷款类型
		String[] loanTypes = getStrArrayl(loanIds);

		String[] follow = getStrArrayl(follows);
		String[] wage = getStrArrayl(wages);
		String[] unit = getStrArrayl(units);
		Integer[] vIds = getIds(visibilitys);

		List<Map<String, Object>> customers = customerListService.searchList(pageIndex, pageSize, dids, eids, statesIds,
				sIds, customerL, loanTypes, beginDate, endDate, orderKey, follow, wage, unit, vIds);
		Integer count = customerListService.searchCount(dids, eids, statesIds, sIds, customerL, loanTypes, beginDate,
				endDate, follow, wage, unit, vIds);
		builderParam(model, pageIndex, pageSize, count,
				"customer/searchValidCustomer.do?depaIds=" + depaIds + "&emplIds=" + emplIds + "&loanIds=" + loanIds
						+ "&clsIds=" + clsIds + "&cstasIds=" + cstasIds + "&csourcesIds=" + csourcesIds + "&follows="
						+ follows + "&wages=" + wages + "&units=" + units + "&visibilitys=" + visibilitys
						+ "&beginDate=" + beginDate + "&endDate=" + endDate + "&orderKey=" + orderKey,
				"customerDiv");

		model.addAttribute("customers", customers);
		model.addAttribute("orderKey", orderKey);
		// 客户来源
		model.addAttribute("sources", customerSourceService.getCustomerSourceList());
		return "page/customer/table-body";
	}

	/**
	 * 回退
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @param depaIds
	 * @param emplIds
	 * @param loanIds
	 * @param clsIds
	 * @param cstasIds
	 * @param csourcesIds
	 * @param beginDate
	 * @param endDate
	 * @param orderKey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rollbackValidCustomer")
	public String rollbackValidCustomer(Integer pageIndex, Integer pageSize, Model model, String depaIds,
			String emplIds, String loanIds, String clsIds, String cstasIds, String csourcesIds, String beginDate,
			String endDate, String orderKey, String follows, String wages, String units, String visibilitys)
			throws Exception {
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		// 部门id
		Integer[] dids = null;
		if (Constant.managerRoleIds.contains(roleId)) {
			// 部门id
			dids = getIds(depaIds);
		} else {
			Integer dId = employee.getDepartment().getId();
			dids = new Integer[1];
			dids[0] = dId;
		}
		// 员工id
		Integer[] eids = null;
		if (!Constant.managerRoleIds.contains(roleId) && Constant.businessManagerRoleId != roleId) {
			eids = new Integer[1];
			eids[0] = employee.getId();
		} else {
			eids = getIds(emplIds);
		}
		// 状态id
		Integer[] statesIds = getIds(cstasIds);
		if (statesIds == null) {
			statesIds = new Integer[4];
			statesIds[0] = 2;
			statesIds[1] = 3;
			statesIds[2] = 8;
			statesIds[3] = 4;
			statesIds = (Integer[]) Constant.validList.toArray(new Integer[Constant.validList.size()]);
		}
		// 来源id
		Integer[] sIds = getIds(csourcesIds);
		// 客户等级
		String[] customerL = getStrArrayl(clsIds);
		// 贷款类型
		String[] loanTypes = getStrArrayl(loanIds);

		String[] follow = getStrArrayl(follows);
		String[] wage = getStrArrayl(wages);
		String[] unit = getStrArrayl(units);
		Integer[] vIds = getIds(visibilitys);
		List<Map<String, Object>> customers = customerListService.searchList(pageIndex, pageSize, dids, eids, statesIds,
				sIds, customerL, loanTypes, beginDate, endDate, orderKey, follow, wage, unit, vIds);
		Integer count = customerListService.searchCount(dids, eids, statesIds, sIds, customerL, loanTypes, beginDate,
				endDate, follow, wage, unit, vIds);
		builderParam(model, pageIndex, pageSize, count,
				"customer/searchValidCustomer.do?depaIds=" + depaIds + "&emplIds=" + emplIds + "&loanIds=" + loanIds
						+ "&clsIds=" + clsIds + "&cstasIds=" + cstasIds + "&csourcesIds=" + csourcesIds + "&follows="
						+ follows + "&wages=" + wages + "&units=" + units + "&visibilitys=" + visibilitys
						+ "&beginDate=" + beginDate + "&endDate=" + endDate + "&orderKey=" + orderKey,
				"customerDiv");

		model.addAttribute("customers", customers);
		model.addAttribute("orderKey", orderKey);

		model.addAttribute("depaIds", depaIds);
		model.addAttribute("emplIds", emplIds);
		model.addAttribute("loanIds", loanIds);
		model.addAttribute("clsIds", clsIds);
		model.addAttribute("cstasIds", cstasIds);
		model.addAttribute("csourcesIds", csourcesIds);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);

		// 客户来源
		model.addAttribute("sources", customerSourceService.getCustomerSourceList());
		return "page/customer/validCustomer";
	}

	private String[] getStrArrayl(String keyStrs) {
		String[] ids = null;
		if (keyStrs != null && !keyStrs.trim().equals("")) {
			ids = keyStrs.split(",");
		}
		return ids;
	}

	private Integer[] getIds(String idstr) {
		Integer[] ids = null;
		if (idstr != null && !idstr.trim().equals("")) {
			String[] idstrArray = idstr.split(",");
			ids = new Integer[idstrArray.length];
			for (int i = 0; i < idstrArray.length; i++) {
				ids[i] = Integer.parseInt(idstrArray[i]);
			}
		}
		return ids;
	}
}
