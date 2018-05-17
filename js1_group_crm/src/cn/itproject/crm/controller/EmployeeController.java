package cn.itproject.crm.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Role;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.RoleService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.MD5Util;
import cn.itproject.crm.util.PropertieFactory;

/**
 * 员工
 * @author ham
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/employee")
public class EmployeeController extends BaseController{
	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private RoleService roleService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("/gotoAdd")
	public String gotoAdd(Model model) throws Exception {
		List<Role> roles = roleService.getEntityAll();
		Employee loginEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		String type = loginEmployee.getType();
		
		if (StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE)) {	// 超级角色
			model.addAttribute("companys", companyService.getAllCompany(null));
		}
		List<Department> departments = departmentService.getByCompanyId(loginEmployee.getCompanyId());
		model.addAttribute("roles", roles);
		model.addAttribute("departments", departments);
		return "page/employee/addEmployee";
	}
	@RequestMapping("/gotoUpdate")
	public String gotoUpdate(Model model,Integer id) throws Exception {
		Employee employee = employeeService.getEmployee(id);
		getRequest().getSession().setAttribute("updateEmployee", employee);
		model.addAttribute("employee", employee);
		model.addAttribute("roles",roleService.getEntityAll());
		Employee loginEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		List<Department> departments = departmentService.getByCompanyId(loginEmployee.getCompanyId());
		model.addAttribute("departments",departments);
		return "page/employee/updateEmployee";
	}
	
	@RequestMapping("/getEmployees")
	public String getEmployees(Integer pageIndex,Integer pageSize,Model model,Integer initPage) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		List<Employee> employees = employeeService.getList(pageIndex,pageSize);
		model.addAttribute("employees", employees);
		
		Integer count = employeeService.getValidCount();
		builderParam(model, pageIndex, pageSize, count, 
				"employee/getEmployees.do?initPage=1", "tableList");
		if (initPage==null) {
			return "page/employee/employee";
		}else {
			return "page/employee/employee-List";
		}
	}
	@RequestMapping(value="addEmployee",method=RequestMethod.POST)
	@ResponseBody
	public Boolean addEmployee(Employee employee,HttpServletRequest request){
		try {
			if (employee==null) {
				return false;
			}
			if (employeeService.getEmployeeByAccount(employee.getAccount())!=null) {
				return false;
			}
			String gender = employee.getGender();
			if ("女".equals(gender)) {
				employee.setHeadImg("upload/head/1.png");
			}else {
				employee.setHeadImg("upload/head/0.png");
			}
			Employee loginEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			if (employee.getCompanyId()==null) {
				employee.setCompanyId(loginEmployee.getCompanyId());
			}
			employee.setPassword(MD5Util.md5("123456"));
			employee.setStatus(Constant.NORMAL);		// 在职
			employeeService.addEmployee(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("updateStatusDimission")
	@ResponseBody
	public Boolean updateStatusDimission(Integer id){
		try {
			Integer count = customerService.getCountByEId(id);
			if (count>0) {
				return false;
			}
			Employee employee = employeeService.getEmployee(id);
			employee.setDimissionDate(new Date());
			employee.setStatus(Constant.DIMISSION);		// 离职
			employee.setName(employee.getName()+"(离职)");
			employeeService.updateEmployee(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@RequestMapping("updateStatusDelete")
	@ResponseBody
	public Boolean updateStatusDelete(Integer id){
		try {
			Integer count = customerService.getCountByEId(id);
			if (count>0) {
				return false;
			}
			Employee employee = employeeService.getEmployee(id);
			employee.setStatus(Constant.DELETE);		// 删除
			employee.setName(employee.getName()+"(删除)");
			employeeService.updateEmployee(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	@RequestMapping("/checkAccountUnique")
	@ResponseBody
	public Boolean checkAccountUnique(String account){
		try {
			return employeeService.getEmployeeByAccount(account)==null;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Boolean update(Employee employee){
		try {
			Employee nowEmployee = (Employee) getRequest().getSession().getAttribute("updateEmployee");
			employee.setPassword(nowEmployee.getPassword());
			employee.setHeadImg(nowEmployee.getHeadImg());
			employee.setCompanyId(nowEmployee.getCompanyId());
			employeeService.updateEmployee(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Boolean updatePassword(String oldPassword,String newPassword){
		Employee nowEmployee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		String nowPassword = nowEmployee.getPassword();
		if (!nowPassword.equals(MD5Util.md5(oldPassword))) {
			return false;
		}
		nowEmployee.setPassword(MD5Util.md5(newPassword));
		try {
			employeeService.updateEmployee(nowEmployee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("/getEmployeesByDId")
	@ResponseBody
	public List<Employee> getEmployeesByDId(Integer dId, Integer cId) throws Exception{
		Integer[] ids = new Integer[1];
		ids[0] = dId;
		List<Employee> employees = employeeService.getEmployeeByDIds(ids);
		return employees;
	}
	
	@RequestMapping("/gotoUploadHead")
	public String gotoUploadHead(Integer id,Model model){
		model.addAttribute("id", id);
		return "page/employee/uploadHead";
	}
	
	@RequestMapping("/uploadHead")
	@ResponseBody
	public Boolean uploadHead(Integer id,
			@RequestParam(value="employeeHeadImg") MultipartFile employeeHeadImg,HttpServletRequest request){
		try {
			Employee employee = employeeService.getEmployee(id);
			String fileName = employeeHeadImg.getOriginalFilename();
			// 保存地址
			String path = PropertieFactory.getProVal("uploadUrl")+File.separator+"upload"+File.separator+"head_"+id+File.separator;
			File folder = new File(path);
			// 如果该目录不存在就创建
			if (!folder.exists()) {
				folder.mkdirs();
			}
			employee.setHeadImg(Constant.UPLOAD_PATH+"/upload/head_"+id+"/"+fileName);
			FileUtils.copyInputStreamToFile(employeeHeadImg.getInputStream(), new File(path, fileName));
			employeeService.updateEmployee(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("/getEmployeeByDId")
	public String getEmployeeByDId(String dIds,Model model) throws Exception{
		if (dIds!=null && !dIds.equals("")) {
			String[] ids = dIds.split(",");
			if (ids!=null && ids.length>0) {
				Integer[] idArray=new Integer[ids.length];
				for (int i = 0; i < idArray.length; i++) {
					idArray[i] = Integer.parseInt(ids[i]);
				}
				model.addAttribute("employees", employeeService.getEmployeeByDIds(idArray));
			}
		}
		return "page/customer/employeeList";
	}
	
	@RequestMapping("/getEmployeeJsonByDId")
	@ResponseBody
	public List<Employee> getEmployeeJsonByDId(String dIds,Model model) throws Exception{
		if (dIds!=null && !dIds.equals("")) {
			String[] ids = dIds.split(",");
			if (ids!=null && ids.length>0) {
				Integer[] idArray=new Integer[ids.length];
				for (int i = 0; i < idArray.length; i++) {
					idArray[i] = Integer.parseInt(ids[i]);
				}
				return employeeService.getEmployeeByDIds(idArray);
			}
		}
		return null;
	}
	
	@RequestMapping("/myInfo")
	public String myInfo(){
		return "page/employee/myInfo";
	}
	/**
	 * 添加员工判断角色是否合法
	 * @param roleId
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("/checkRole")
	@ResponseBody
	public boolean checkRole(Integer roleId,Integer departmentId) throws Exception{
		return employeeService.checkRole(roleId,departmentId);
	}
	
	/**
	 * 通过部门ID获取员工id/name集合
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getEmployeeListMapByDepartmentId")
	@ResponseBody
	public List<Map<String, Object>> getEmployeeListMapByDepartmentId(Integer departmentId) throws Exception {
		return employeeService.getEmployeeListMapByDepartmentId(departmentId);
	}
	
	@RequestMapping("/getCustomerNO")
	@ResponseBody
	public List<Map<String, Object>> getCustomerNO(Integer employeeId) throws Exception {
		return employeeService.getCustomerNO(employeeId);
	}
	
	@RequestMapping("/saveCustomerNO")
	@ResponseBody
	public boolean getCustomerNO(Integer eId, Integer currentNO) throws Exception {
		try {
			employeeService.saveCustomerNO(eId,currentNO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}


