package cn.itproject.crm.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Menu;
import cn.itproject.crm.bean.Role;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.MenuTree;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.LendingInstitutionService;
import cn.itproject.crm.service.MenuService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.MD5Util;
import cn.itproject.crm.util.SingleLogin;

/**
 * 登录控制器
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月14日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
public class LoginController extends BaseController {

	@Resource
	private EmployeeService employeeService;

	@Resource
	private MenuService menuService;

	@Resource
	private CompanyService companyService;

	@Resource
	private LendingInstitutionService lendingInstitutionService;

	/**
	 * 判断员工输入的账号密码是否正确
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public String login(Employee employee, Integer flag, HttpServletResponse response, Model model) throws Exception {
		if (employee == null || employee.getAccount() == null || employee.getPassword() == null
				|| employee.getAccount().equals("") || employee.getPassword().equals("")) {
			model.addAttribute("login_isNull", 1);
			return "login";
		}
		Employee loginEmployee = employeeService.getEmployee(employee.getAccount(),
				MD5Util.md5(employee.getPassword()));
		// 登陆成功
		if (loginEmployee != null) {
			// 如果员工持有客户数量上限为0，则拉去role客户数量，初始化员工客户数量上限
			/*
			 * if(loginEmployee.getCustomerNO()==0){
			 * loginEmployee.setCustomerNO(loginEmployee.getRole().getCustomerNO
			 * ());
			 * System.out.println("====================customerNO:============="
			 * +loginEmployee.getCustomerNO()); }
			 */

			getRequest().getSession().setAttribute(Constant.LOGIN_USER, loginEmployee);
			SingleLogin.singleLogin(getRequest().getSession(), loginEmployee.getAccount()); // 单点登录
			Integer[] menusIds = null;
			Role role = null;
			role = loginEmployee.getRole();
			System.out.println(role.getMenus());
			if (loginEmployee.getId() != 999) {// 超级管理员
				String menus = role.getMenus();
				String[] menuArray = menus.split("-");
				menusIds = new Integer[menuArray.length];
				for (int i = 0; i < menuArray.length; i++) {
					menusIds[i] = Integer.parseInt(menuArray[i]);
				}
				for (Integer i : menusIds) {
					System.out.println(i);
				}
			}

			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			List<Menu> parentMenus = menuService.getParentMenus(menusIds);
			for (Menu pm : parentMenus) {
				MenuTree menuTree = new MenuTree(pm, new ArrayList<Menu>());
				menuTrees.add(menuTree);
			}
			for (Menu menu : parentMenus) {
				System.out.println(menu.getName() + "~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}

			List<Menu> leafMenus = menuService.getLeafMenu(menusIds);
			for (Menu lm : leafMenus) {
				for (MenuTree tree : menuTrees) {
					if (lm.getParent().equals(tree.getMenu())) {
						tree.getMenus().add(lm);
					}
				}
			}
			List<MenuTree> nowTrees = new ArrayList<>();
			for (MenuTree tree : menuTrees) {
				if (tree.getMenus().size() > 0) {
					nowTrees.add(tree);
				}
			}
			getRequest().getSession().setAttribute("menuTrees", nowTrees);
			// 获取菜单
			// 记录cookie
			if (flag != null && flag == 1) {
				String name = URLEncoder.encode(loginEmployee.getAccount(), "UTF-8");
				Cookie cookie = new Cookie("account", name);
				cookie.setMaxAge(60 * 60 * 24 * 15);
				cookie.setPath("/");

				response.addCookie(cookie);
			}

			// 获取所有谈判师
			List<Employee> allNegotiators = employeeService.getAllNegotiators();
			setSession("allNegotiators", allNegotiators);
			if (role != null) {
				System.out.println(role.getId());
				if (Constant.managerRoleIds.contains(role.getId())
						|| Constant.administrationRoleIds.contains(role.getId())) {
					setSession("administration", true);
				}
				if (Constant.managerRoleIds.contains(role.getId())) {
					setSession("managerRole", true);
				}
			}

			Integer loginRoleId = role.getId();
			// 当前登录用户的角色ID
			setSession(Constant.LOGIN_ROLE_ID, loginRoleId);

			// 生成客户详情的token,当打开单独的客户详情页面时,只针对当前登录用户有效,避免盗链
			// 使用当前登录用户的角色ID,用户ID,用户账号,进行MD5加密作为客户详情的token
			Integer loginEmployeeId = employee.getId();
			String loginAccount = employee.getAccount();
			String customerDetailToken = MD5Util.md5(loginRoleId + "-" + loginEmployeeId + "-" + loginAccount);
			setSession(Constant.CUSTOMER_DETAIL_TOKEN, customerDetailToken);

			// 系统头部图片
			String systemHeaderLogo = "upload/JS_LOGO.png";

			if (loginEmployee.getCompanyId() != null && loginEmployee.getCompanyId() == 2) { // 信美达
				systemHeaderLogo = "upload/JS_LOGO.png";
			}
			setSession(Constant.SYSTEM_HEADER_LOGO, systemHeaderLogo);

			Integer currentRoleId = getLoginEmployee().getRole().getId();
			// 如果是超级管理员,超级权证,权证中心管理员,权证部门经理,权证专员,可以放款,退单退件
			if (Constant.canLoanRoles.contains(currentRoleId)) {
				setSession(Constant.CAN_LOAN_PERMISSION, true);
			} else {
				setSession(Constant.CAN_LOAN_PERMISSION, false);
			}
			// 如果是超级权证,权证中心管理员,权证部门经理,权证专员的客户跟进类型与业务部不一样
			if (Constant.warrantRoles.contains(currentRoleId)) {
				setSession(Constant.WARRANT_ROLE, true);
			} else {
				setSession(Constant.WARRANT_ROLE, false);
			}

			return "redirect:/index.jsp";
		} else {
			model.addAttribute("login_error", 1);
			return "login";
		}
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		Employee loginEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		if (loginEmployee != null) {
			SingleLogin.logout(getRequest().getSession(), loginEmployee.getAccount());
		}
		return "redirect:/login.jsp";
	}

	/**
	 * 锁屏
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lock")
	public String lock() {
		Employee loginEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		if (loginEmployee == null) {
			return "redirect:/login.jsp";
		}
		loginEmployee.setLoginLock(true);
		return "redirect:/extra_lock.jsp";
	}

	@RequestMapping(value = "/unLock")
	public String unLock(String password, Model model) {
		Employee loginEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		if (loginEmployee == null) {
			return "redirect:/login.jsp";
		}
		if (password != null && loginEmployee.getPassword().equals(MD5Util.md5(password))) {
			loginEmployee.setLoginLock(false);
			return "redirect:/index.jsp";
		}
		model.addAttribute("unLockError", 1);
		return "extra_lock";
	}
}