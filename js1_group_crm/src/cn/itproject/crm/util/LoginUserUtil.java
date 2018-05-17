package cn.itproject.crm.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.SystemContext;

public class LoginUserUtil {
	
	/**
	 * 获取登录用户类型
	 * @return
	 */
	public static String getUserType(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee loginEmployee = (Employee)request.getSession().getAttribute(Constant.LOGIN_USER);
		String type = loginEmployee.getType();
		return type==null?"":type;
	}
	
	/**
	 * 获取登录用户名称
	 * @return
	 */
	public static String getUserName(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee loginEmployee = (Employee)request.getSession().getAttribute(Constant.LOGIN_USER);
		String name = loginEmployee.getName();
		return name==null?"":name;
	}
	
	/**
	 * 获取公司id
	 * @return
	 */
	public static Integer getCompanyId(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee loginEmployee = (Employee)request.getSession().getAttribute(Constant.LOGIN_USER);
		Integer companyId = loginEmployee.getCompanyId();
		return companyId;
	}
	
	public static Integer[] getCompanyIds(){
		String companyIds = SystemContext.getSyscontext().getCompanyIds();
		Integer[] ids = null;
		if (companyIds!=null && !companyIds.trim().equals("")) {
			String[] idstrArray = companyIds.split(",");
			ids = new Integer[idstrArray.length];
			for (int i = 0; i < idstrArray.length; i++) {
				ids[i] = Integer.parseInt(idstrArray[i]);
			}
		}
		return ids;
	}
	
	//判断是否为权证部门
	public static boolean isWarrantRole() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		boolean flag = (boolean) request.getSession().getAttribute(Constant.WARRANT_ROLE);
		//boolean flag = Constant.warrantRoles.contains(((Employee)request.getSession().getAttribute(Constant.LOGIN_USER)).getRole().getId());
		return flag;
	}	
	public static Integer getEmployeeId(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee loginEmployee = (Employee)request.getSession().getAttribute(Constant.LOGIN_USER);
		Integer id = loginEmployee.getId();
		return id;
	}
	
	/**
	 * 获取当前登录角色ID
	 * @return
	 */
	public static Integer getLoginRoleId() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return (Integer)request.getSession().getAttribute(Constant.LOGIN_ROLE_ID);
	}
	
	/**
	 * 获取当前登录用户的部门ID
	 * @return
	 */
	public static Integer getLoginDepartmentId() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Employee loginEmployee = (Employee)request.getSession().getAttribute(Constant.LOGIN_USER);
		return loginEmployee.getDepartment().getId();
	}
}
