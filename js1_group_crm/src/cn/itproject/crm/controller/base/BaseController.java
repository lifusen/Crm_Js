package cn.itproject.crm.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.CustomerSourceService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

public class BaseController {
	protected Integer pageSize = 10;

	@Resource
	private CompanyService companyService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private EmployeeService employeeService;

	@Resource
	private CustomerSourceService customerSourceService;

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	protected void builderParam(Model model, Integer pageIndex, Integer pageSize, Integer count, String url,
			String loadId) {
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", pageSize);
		if (count != null && count > 0) {
			model.addAttribute("totalPage", (count + pageSize - 1) / pageSize);
		} else {
			model.addAttribute("totalPage", 0);
			model.addAttribute("pageIndex", 0);
		}
		model.addAttribute("count", count);
		model.addAttribute("url", url); // 请求地址
		model.addAttribute("loadId", loadId); // 加载区域
	}

	protected Integer initPageIndex(Integer pageIndex) {
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		return pageIndex;
	}

	protected Integer initPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 1) {
			pageSize = this.pageSize;
		}
		return pageSize;
	}

	/**
	 * 获取登陆用户
	 * 
	 * @return
	 */
	protected Employee getLoginEmployee() {
		return (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
	}
	
	/**
	 * 获取登陆用户的公司对象
	 * 
	 * @return
	 */
	protected Company getLoginCompany() {
		return (Company) getRequest().getSession().getAttribute(Constant.LOGIN_COMPANY);
	}

	/**
	 * 获取真实路径
	 * 
	 * @param path
	 * @return
	 */
	protected String getRealPath(String path) {
		return getRequest().getServletContext().getRealPath(path);
	}

	/**
	 * 错误的消息Map
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> errorMsgMap(Object msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 消息Map
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> msgMap(Object msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", true);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 向Session作用域存储值
	 * 
	 * @param key
	 * @param value
	 */
	protected void setSession(String key, Object value) {
		getRequest().getSession().setAttribute(key, value);
	}
	
	/**
	 * 向Application作用域存储值
	 * 
	 * @param key
	 * @param value
	 */
	protected void setApplication(String key, Object value) {
		getRequest().getServletContext().setAttribute(key, value);
	}
	
	/**
	 * 从Application作用域获取值
	 * 
	 * @param key
	 * @param value
	 */
	protected Object getApplication(String key) {
		return getRequest().getServletContext().getAttribute(key);
	}
	
	/**
	 * 从session中获取值
	 * 根据key从session中获取值
	 * @param key
	 * @return
	 */
	protected Object getSession(String key) {
		return getRequest().getSession().getAttribute(key);
	}

	protected String getContextPath() {
		return getRequest().getContextPath() + "/";
	}

	// ------------------------------ 缓存的公司/部门/员工/客户来源 start ------------------------------
	/**
	 * 将公司Map保存到model中
	 * 
	 * @param model
	 * @throws Exception
	 */
	protected void setCompanyCacheMap(Model model) throws Exception {
		Map<Integer, String> companyCacheMap = companyService.getAllCompanyMap();
		model.addAttribute("companyCacheMap", companyCacheMap);
	}

	/**
	 * 将部门Map保存到model中
	 * 
	 * @param model
	 * @throws Exception
	 */
	protected void setDepartmentCacheMap(Model model) throws Exception {
		Map<Integer, String> departmentCacheMap = departmentService.getAllDepartmentMap();
		model.addAttribute("departmentCacheMap", departmentCacheMap);
	}

	/**
	 * 将员工Map保存到model中
	 * 
	 * @param model
	 * @throws Exception
	 */
	protected void setEmployeeCacheMap(Model model) throws Exception {
		Map<Integer, String> employeeCacheMap = employeeService.getAllEmployeeMap();
		model.addAttribute("employeeCacheMap", employeeCacheMap);
	}

	/**
	 * 将客户来源Map保存到model中
	 * 
	 * @param model
	 * @throws Exception
	 */
	protected void setCustomerSourceCacheMap(Model model) throws Exception {
		Map<Integer, String> customerSourceCacheMap = customerSourceService.getAllCustomerSourceMap();
		model.addAttribute("customerSourceCacheMap", customerSourceCacheMap);
	}
	// ------------------------------ 缓存的公司/部门/员工/客户来源 end --------------------------------

}