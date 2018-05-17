package cn.itproject.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.ContractManager;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.ContractManaListService;
import cn.itproject.crm.service.ContractManagerService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

@Controller
@Scope("prototype")
@RequestMapping("/contractManager")
public class ContractManagerController extends BaseController {

	@Resource
	private ContractManagerService contractManagerService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private CompanyService companyService;
	@Resource
	private ContractManaListService contractManaListService;

	@RequestMapping("/showContractManager")
	public String showContractManagerUI() {
		return "page/contractManager/contractMana";
	}

	@RequestMapping("/addContractManager")
	@ResponseBody
	public String addContractManager(ContractManager contractManager, Employee employee) {
		try {
			contractManagerService.addManagerContract(contractManager, getLoginEmployee());
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/getValidContractManager")
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
				applyPerson = contractManaListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, keyStr);
				count = contractManaListService.getCountByStateAndEId(Constant.validList, ids, keyStr);
			}
			getEmplloyByDids(model, new Integer[] { employee.getDepartment().getId() });
		} else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			applyPerson = contractManaListService.getCustomersByStateAndEId(pageIndex, pageSize, ids, keyStr);
			count = contractManaListService.getCountByStateAndEId(Constant.validList, ids, keyStr);
		}
		builderParam(model, pageIndex, pageSize, count, "applyPerson/getValidPersonList.do?initPage=1&keyStr=" + keyStr,
				"tableList");
		System.out.println();
		model.addAttribute("roleId", roleId);
		model.addAttribute("customers", applyPerson);
		model.addAttribute("keyStr", keyStr);
		if (srarchDefault != null && srarchDefault == 1) {
			return "page/contractManager/table-body";
		}
		if (initPage == null) {
			return "page/contractManager/validCustomer";
		}
		return "page/contractManager/validCustomer-List";
	}

	@RequestMapping("/updateContractManaList")
	public String updateContractManagerUI(Model model, Integer id, String refererUrl, String menuAttrId,
			String showButton, Boolean isShowFollow, Boolean onlyShowFollowList, Boolean noShowSaveButton,
			Boolean commonPool, Integer updateType) throws Exception {
		System.out.println("跳转到客户列表基本信息修改页面.......");
		// 获取合同信息
		System.out.println(id + "好多好多的ID");
		ContractManager contractManager = contractManagerService.getContManaById(id);
		if (refererUrl != null) {
			refererUrl = refererUrl.replace(",", "&");
		}
		// 合同来源
		model.addAttribute("contractManager", contractManager);
		model.addAttribute("refererUrl", refererUrl);
		model.addAttribute("menuAttrId", menuAttrId);
		model.addAttribute("showButton", showButton);
		model.addAttribute("isShowFollow", isShowFollow);
		model.addAttribute("onlyShowFollowList", onlyShowFollowList);
		model.addAttribute("noShowSaveButton", noShowSaveButton);
		model.addAttribute("commonPool", commonPool);
		model.addAttribute("updateCustomerId", id);
		model.addAttribute("updateType", updateType);
		return "page/contractManager/updateContractManaList";
	}

	private void getEmplloyByDids(Model model, Integer[] dIds) throws Exception {
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(ContractManager contractManager) throws Exception {
		try {
			System.out.println(contractManager.getId() + "askljdlkasjd");
			contractManagerService.updateManagerContract(contractManager);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
