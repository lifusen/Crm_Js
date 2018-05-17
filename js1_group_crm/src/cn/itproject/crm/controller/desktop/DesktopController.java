package cn.itproject.crm.controller.desktop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

/**
 * 桌面控制器
 * 
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopController extends BaseController {
	@RequestMapping("/getDesktop")
	public String getDesktop(Integer type) {
		String prefix = "page/desktop/";
		String page = "";

		if (LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			page = "welcome_crm";
			return prefix + page;
		}

		// 4个桌面:1.销售总监,2.部门经理,3.业务员桌面,4.行政专员
		Employee loginEmployee = getLoginEmployee();
		Integer roleId = loginEmployee.getRole().getId();

		if (Constant.managerRoleIds.contains(roleId)) { // 管理层
			page = "ywbgl_desktop";
		} else if (Constant.businessManagerRoleId == roleId) { // 部门经理
			page = "ywbgl_desktop";
		} else if (Constant.administrationRoleIds.contains(roleId)) { // 行政部门
			page = "xz_desktop";
		} else { // 业务员
			page = "ywy_desktop";
		}
		return prefix + page;
	}
}
