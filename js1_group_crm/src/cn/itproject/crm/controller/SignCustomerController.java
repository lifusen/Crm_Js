package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.swing.JComboBox.KeySelectionManager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.CDEVo;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.FailureMessageService;
import cn.itproject.crm.service.NotificationService;
import cn.itproject.crm.service.OutLoanBelongService;
import cn.itproject.crm.service.SignCustomerService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.DateUtil;
import cn.itproject.crm.util.LoginUserUtil;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月3日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/signCustomer")
public class SignCustomerController extends BaseController {

	@Resource
	private SignCustomerService signCustomerService;

	@Resource
	private EmployeeService employeeService;

	@Resource
	private FailureMessageService failureMessageService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private NotificationService notificationService;

	@Resource
	private OutLoanBelongService outLoanBelongService;

	@RequestMapping("/addSignCustomer")
	@ResponseBody
	public Boolean addSignCustomer(SignCustomer signCustomer) throws Exception {
		try {
			System.out.println(signCustomer);

			signCustomerService.addSignCustomer(signCustomer, getLoginEmployee());
			// 权证公司ID
			Integer warrantCompanyId = signCustomer.getWarrantCompanyId();
			if (warrantCompanyId != null && warrantCompanyId > 0) {
				// 修改权证信息
				signCustomerService.updateWarrant(new Integer[] { signCustomer.getId() },
						signCustomer.getWarrantCompanyId(), signCustomer.getWarrantDepartmentId(),
						signCustomer.getWarrantEmployeeId());

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping("/updateSignCustomer")
	@ResponseBody
	public Boolean updateSignCustomer(SignCustomer signCustomer, boolean isUpdateWarrant) throws Exception {
		try {
			System.out.println(signCustomer + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			signCustomerService.updateSignCustomer(signCustomer, getLoginEmployee());

			// 修改权证信息,如果修改之前的权证公司ID等于null,则表示还未移交给权证,可以修改
			if (isUpdateWarrant) {
				// 判断当前的权证公司ID是否有值,如果有,则修改权证信息
				Integer currentWarrantCompanyId = signCustomer.getWarrantCompanyId();
				if (currentWarrantCompanyId != null && currentWarrantCompanyId > 0) {
					signCustomerService.updateWarrant(new Integer[] { signCustomer.getId() },
							signCustomer.getWarrantCompanyId(), signCustomer.getWarrantDepartmentId(),
							signCustomer.getWarrantEmployeeId());

					// ----------->2017.1.10 开会指出，业务员只能选择中心，不再指定权证专员，所以取消消息发送（蒲）
					// Integer senderId = getLoginEmployee().getId(); // 发送人
					// 发送通知
					// notificationService.addNotification(senderId,
					// signCustomer);

				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 如果是签单客户列表,有签单ID,则直接查询改客户 如果没有签单ID,则查询当前客户最后一个签单客户的信息
	 * 
	 * @param customerId
	 * @param signCustomerId
	 * @param refererUrl
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUpdateSignCustomerListNew")
	public String getUpdateSignCustomerListNew(Integer customerId, Integer signCustomerId, String refererUrl,
			Model model) throws Exception {
		// 签单客户
		SignCustomer signCustomer = signCustomerService.getSignCustomer(customerId, signCustomerId);
		// 获取主贷辅贷类型
		Integer outLoanBelongType = -1;
		if (LoginUserUtil.isWarrantRole()) {
			outLoanBelongType = outLoanBelongService.getType(signCustomer.getId(), LoginUserUtil.getEmployeeId());
		}
		// 获取签单、权证关系
		List<OutLoanBelong> outLoanBelongs = outLoanBelongService.getOutLoanBelong(signCustomerId);
		// 客户资产
		List<CollateralViewBean> sessionAssets = (List<CollateralViewBean>) getRequest().getSession()
				.getAttribute("customerAssetViewBeans");
		if (sessionAssets != null && sessionAssets.size() > 0) {
			List<CollateralViewBean> noneSelectedAssets = new ArrayList<CollateralViewBean>();
			noneSelectedAssets.addAll(sessionAssets);
			List<CollateralViewBean> cvbs = signCustomer.getCollateralViewBeans();
			if (cvbs != null && cvbs.size() > 0) {
				noneSelectedAssets.removeAll(cvbs);
			}
			System.out.println("-------------------------------------------------->");
			System.out.println("sessionAssets:" + sessionAssets);
			System.out.println("noneSelectedAssets:" + noneSelectedAssets);
			System.out.println("cvbs:" + cvbs);
			model.addAttribute("noneSelectedAssets", noneSelectedAssets);
		}

		// 计算办理周期
		Integer diffDay = DateUtil.getDiffDay(signCustomer.getSignTime());
		model.addAttribute("diffDay", diffDay);
		// 签单客户
		model.addAttribute("signCustomer", signCustomer);
		model.addAttribute("refererUrl", refererUrl);
		// 签单权证关系
		model.addAttribute("outLoanBelongType", outLoanBelongType);
		model.addAttribute("outLoanBelongs", outLoanBelongs);
		// 构建参数
		builderParam(model, 1, 1, 1, "signCustomer/getUpdateSignCustomerListNew.do?customerId=" + customerId,
				"updateSignCustomerListArea");

		// 设置公司/部门/员工数据
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		setEmployeeCacheMap(model);

		return "page/signCustomer/listAndUpdate";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/getUpdateSignCustomerList")
	public String getUpdateSignCustomerList(Integer pageIndex, Integer initPage, Integer customerId, Integer sId,
			String refererUrl, boolean closeWindow, Model model) throws Exception {
		pageIndex = initPageIndex(pageIndex);
		/**
		 * 如果是签到列表双击进入,有sId(签单客户ID),获取到该客户所有的签单ID,并指定sId对应的页数 如果是有效客户列表双击进入,分页查询
		 */
		Integer pageSize = 1;
		// 获取到该客户下的所有签单ID
		List<Integer> signCustomerIds = signCustomerService.getSignCustomerIdsByCId(customerId);
		// 签单客户
		SignCustomer signCustomer = null;
		// 是否为主贷人员,主贷0、辅贷1；
		List<OutLoanBelong> outLoanBelongs = null;
		Integer outLoanBelongType = -1;
		// 总数
		Integer count = 0;
		// 如果有签单客户
		if (signCustomerIds != null && signCustomerIds.size() > 0) {
			count = signCustomerIds.size(); // 总行数
			if (initPage == null && sId != null) {// 签单客户列表双击进入
				// 1,5,6
				// 6:索引为2,页码为3
				pageIndex = signCustomerIds.indexOf(sId) + 1;
			}
			// 获取分页列表
			signCustomer = signCustomerService.getSignCustomerByCustomerId(pageIndex, pageSize, customerId);
			// 获取主贷辅贷类型
			// if(sId==null || sId<=0){
			outLoanBelongs = outLoanBelongService.getOutLoanBelong(signCustomer.getId());
			// }
			if (LoginUserUtil.isWarrantRole()) {
				outLoanBelongType = outLoanBelongService.getType(signCustomer.getId(), LoginUserUtil.getEmployeeId());
			}
			// 客户资产
			List<CollateralViewBean> sessionAssets = (List<CollateralViewBean>) getRequest().getSession()
					.getAttribute("customerAssetViewBeans");
			if (sessionAssets != null && sessionAssets.size() > 0) {
				List<CollateralViewBean> noneSelectedAssets = new ArrayList<CollateralViewBean>();
				noneSelectedAssets.addAll(sessionAssets);
				List<CollateralViewBean> cvbs = signCustomer.getCollateralViewBeans();
				if (cvbs != null && cvbs.size() > 0) {
					noneSelectedAssets.removeAll(cvbs);
				}
				System.out.println("-------------------------------------------------->");
				System.out.println("sessionAssets:" + sessionAssets);
				System.out.println("noneSelectedAssets:" + noneSelectedAssets);
				System.out.println("cvbs:" + cvbs);
				model.addAttribute("noneSelectedAssets", noneSelectedAssets);
			}

			// 计算办理周期
			Integer diffDay = DateUtil.getDiffDay(signCustomer.getSignTime());
			model.addAttribute("diffDay", diffDay);

		}
		// 签单客户
		model.addAttribute("signCustomer", signCustomer);
		model.addAttribute("refererUrl", refererUrl);
		// 所属权证
		model.addAttribute("outLoanBelongType", outLoanBelongType);
		model.addAttribute("outLoanBelongs", outLoanBelongs);
		// 构建参数
		builderParam(model, pageIndex, pageSize, count,
				"signCustomer/getUpdateSignCustomerList.do?initPage=1&customerId=" + customerId,
				"updateSignCustomerListArea");

		// 设置公司/部门/员工数据
		setCompanyCacheMap(model);
		setDepartmentCacheMap(model);
		setEmployeeCacheMap(model);

		model.addAttribute("closeWindow", closeWindow);

		return "page/signCustomer/listAndUpdate";
	}

	@RequestMapping("getSignCustomerList")
	public String getSignCustomerList(Integer pageIndex, Integer pageSize, Integer initPage, String keyword,
			Model model, Integer state, Integer dId, Integer eId, String beginDate, String endDate) throws Exception {

		if (beginDate == null || endDate == null || "".equals(beginDate) || "".equals(endDate)) {
			beginDate = "";
			endDate = "";
		}

		dId = (dId == null ? 0 : dId);
		eId = (eId == null ? 0 : eId);
		keyword = (keyword == null ? "" : keyword);
		state = (state == null ? 0 : state);
		List<Department> departments = departmentService.getDeparmentByName("业务");
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		List<Integer> signList = null;
		if (state == 0) {
			signList = Arrays.asList(CustomerState.sign.ordinal());
		} else if (state == 1) {
			signList = Arrays.asList(CustomerState.chargeback.ordinal(), CustomerState.reject.ordinal());
		} else if (state == 2) {
			signList = Arrays.asList(CustomerState.outLoan.ordinal());
		} else if (state == 3) {
			signList = Arrays.asList(CustomerState.sign.ordinal(), CustomerState.outLoan.ordinal(),
					CustomerState.chargeback.ordinal(), CustomerState.reject.ordinal());
		}
		// 登陆对象
		Employee loginEmployee = getLoginEmployee();
		// 登陆对象的角色ID
		Integer roleId = loginEmployee.getRole().getId();
		// 签单客户列表
		List<Map<String, Object>> signCustomers = new ArrayList<Map<String, Object>>();
		// 总行数
		Integer count = 0;
		// 员工ID集合
		List<Integer> employeeIds = new ArrayList<Integer>();

		if (Constant.managerRoleIds.contains(roleId)) { // 管理层
			if (eId > 0) {
				employeeIds.add(eId);
			} else {
				if (dId > 0) {
					Integer[] dids = new Integer[1];
					dids[0] = dId;
					List<Employee> employees = employeeService.getEmployeeByDIds(dids);
					if (employees != null && employees.size() > 0) {
						for (int i = 0; i < employees.size(); i++) {
							employeeIds.add(employees.get(i).getId());
						}
					}
				} else {
					employeeIds = null;
				}
			}
		} else if (Constant.businessManagerRoleId == roleId) { // 业务部门经理
			if (eId > 0) {
				employeeIds.add(eId);
			} else {
				// 获取该部门下的所有员工
				employeeIds = employeeService.getEmployeeIdsByDId(loginEmployee.getDepartment().getId());
			}
			getEmplloyByDids(model, new Integer[] { loginEmployee.getDepartment().getId() });
		} else { // 业务员
			employeeIds.add(loginEmployee.getId());
		}

		// 获取列表
		signCustomers = signCustomerService.getSignCustomerList(pageIndex, pageSize, employeeIds, keyword, signList,
				beginDate, endDate);
		// 获取总行数
		count = signCustomerService.getSignCustomerListCount(employeeIds, keyword, signList, beginDate, endDate);
		System.out.println("signCustomers==============>");
		System.out.println(count);

		String listUIString = "page/signCustomer/listUI";
		String listString = "page/signCustomer/list";
		String loadUrlString = "signCustomer/getSignCustomerList.do?initPage=1&keyword=" + keyword + "&state=" + state
				+ "&dId=" + dId + "&eId=" + eId + "&beginDate=" + beginDate + "&endDate=" + endDate;

		builderParam(model, pageIndex, pageSize, count, loadUrlString, "tableList");
		model.addAttribute("signCustomers", signCustomers);
		model.addAttribute("departments", departments);
		model.addAttribute("keyStr", keyword);
		model.addAttribute("dId", dId);
		model.addAttribute("eId", eId);
		model.addAttribute("state", state);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("roleId", roleId);
		if (initPage == null) {
			return listUIString;
		}
		return listString;
	}

	@RequestMapping("getOutLoanCustomerList")
	public String getOutLoanCustomerList(Integer pageIndex, Integer initPage, String keyword, Model model,
			String beginDate, String endDate) throws Exception {
		pageIndex = initPageIndex(pageIndex);

		// 登陆对象
		Employee loginEmployee = getLoginEmployee();
		// 登陆对象的角色ID
		Integer roleId = loginEmployee.getRole().getId();
		// 签单客户列表
		List<Map<String, Object>> signCustomers = new ArrayList<Map<String, Object>>();
		// 总行数
		Integer count = 0;
		// 员工ID集合
		List<Integer> employeeIds = new ArrayList<Integer>();

		if (Constant.managerRoleIds.contains(roleId)) { // 管理层
			employeeIds = null;
		} else if (Constant.businessManagerRoleId == roleId) { // 业务部门经理
			// 获取该部门下的所有员工
			employeeIds = employeeService.getEmployeeIdsByDId(loginEmployee.getDepartment().getId());
		} else { // 业务员
			employeeIds.add(loginEmployee.getId());
		}

		// 获取列表
		signCustomers = signCustomerService.getSignCustomerList(pageIndex, pageSize, employeeIds, keyword,
				Constant.outLoanList, beginDate, endDate);
		// 获取总行数
		count = signCustomerService.getSignCustomerListCount(employeeIds, keyword, Constant.outLoanList, beginDate,
				endDate);
		System.out.println("signCustomers==============>");
		System.out.println(count);

		String listUIString = "page/outLoanCustomer/listUI";
		String listString = "page/outLoanCustomer/list";
		String loadUrlString = "signCustomer/getOutLoanCustomerList.do?initPage=1";

		builderParam(model, pageIndex, pageSize, count, loadUrlString, "tableList");
		model.addAttribute("signCustomers", signCustomers);
		if (initPage == null) {
			return listUIString;
		}
		return listString;
	}

	@RequestMapping("signCustomerMaintainList")
	public String signCustomerMaintainList(Integer pageIndex, Integer pageSize, Integer initPage, String keyword,
			Model model) throws Exception {
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		// 签单客户维护列表
		List<Map<String, Object>> maintains = new ArrayList<Map<String, Object>>();
		// 总行数
		Integer count = 0;
		// 获取列表
		maintains = signCustomerService.getMaintainList(pageIndex, pageSize, keyword);
		// 获取总行数
		count = signCustomerService.getMaintainCount(keyword);

		String listUIString = "page/administrative/signCustomerMaintain";
		String listString = "page/administrative/maintain-list";
		String loadUrlString = "signCustomer/signCustomerMaintainList.do?initPage=1&keyword="
				+ (keyword == null ? "" : keyword);

		builderParam(model, pageIndex, pageSize, count, loadUrlString, "tableList");
		model.addAttribute("maintains", maintains);
		if (initPage == null) {
			return listUIString;
		}
		return listString;
	}

	@RequestMapping("/signCustomerEvaluate")
	@ResponseBody
	public boolean signCustomerEvaluate(String val) {
		if (val == null || val.equals("")) {
			return false;
		}
		String[] vals = val.split("-");
		if (vals == null || vals.length < 1) {
			return false;
		}
		String level = vals[0];
		Integer sId = Integer.parseInt(vals[1]);
		try {
			signCustomerService.evaluate(level, sId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void getEmplloyByDids(Model model, Integer[] dIds) throws Exception {
		model.addAttribute("employees", employeeService.getEmployeeByDIds(dIds));
	}

	@RequestMapping("/getSignCustomerSummaryInfo")
	@ResponseBody
	public List<Map<String, Object>> getSignCustomerSummaryInfo(Integer customerId) throws Exception {
		List<Map<String, Object>> list = signCustomerService.getSignCustomerSummaryInfo(customerId);
		return list;
	}
}
