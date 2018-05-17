package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

/**
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/department")
public class DepartmentController extends BaseController{

	@Resource
	private DepartmentService departmentService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CompanyService companyService;

	/**
	 * 分页获取部门
	 * @param model
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/departmentList")
	public String roleList(Model model,Integer pageIndex) throws Exception{
//		pageIndex = initPageIndex(pageIndex);
		model.addAttribute("departments", departmentService.getAll());
		return "page/department/departmentList";
	}
	
	/**
	 * 添加部门
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addDepartment")
	@ResponseBody
	public Boolean addDepartment(Department department) throws Exception{
		try {
			if (department==null) {
				return false;
			}
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			String type = employee.getType();
			if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			}else {
				department.setCompanyId(employee.getCompanyId());
			}
			departmentService.addDepartment(department);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取修改的部门
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoUpdate")
	public String getDepartment(Integer id,Model model) throws Exception{
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(null));
		}
		// TODO
		Department department = departmentService.getDepartment(id);
		model.addAttribute("department", department);
		getRequest().getSession().setAttribute("updateDepartment", department);
		return "page/department/updateDepartment";
	}

	/**
	 * 添加部门
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoAdd")
	public String gotoAdd(Model model) throws Exception{
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(null));
		}
		return "page/department/addDepartment";
	}
	
	/**
	 * 修改
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateDepartment")
	@ResponseBody
	public Boolean update(Department department){
		try {
			Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
			String type = employee.getType();
			if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			}else {
				department.setCompanyId(employee.getCompanyId());
			}
			departmentService.updateDepartment(department);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取当前部门下是否有员工
			Integer[] ids = new Integer[1];
			ids[0] = id;
			List<Employee> employees = employeeService.getEmployeeByDIds(ids);
			if (employees!=null && employees.size()>0) {
				map.put("flag", false);
				map.put("msg", "该部门下有员工，不能删除！");
				return map;
			}
			departmentService.deleteDepartment(id);
			map.put("flag", true);
		} catch (Exception e) {
			map.put("flag", false);
			map.put("msg", "删除失败，稍后再试...");
		}
		return map;
	}
	@RequestMapping("/getByCompanyId")
	@ResponseBody
	public List<Department> getByCompanyId(Integer companyId) throws Exception{
		return departmentService.getByCompanyId(companyId);
	}
	
	@RequestMapping("/getByCompanyIds")
	public String getByCompanyIds(String companyIds,Model model) throws Exception{
		if (companyIds!=null && !companyIds.equals("")) {
			String[] ids = companyIds.split(",");
			if (ids!=null && ids.length>0) {
				Integer[] idArray=new Integer[ids.length];
				for (int i = 0; i < idArray.length; i++) {
					idArray[i] = Integer.parseInt(ids[i]);
				}
				model.addAttribute("departments", departmentService.getDepartmentByCompanyIds(idArray,"业务"));
			}
		}
		return "page/customer/companyList";
	}
	
	@RequestMapping("/getByCompanyJsonIds")
	@ResponseBody
	public List<Department> getByCompanyJsonIds(String companyIds,Model model) throws Exception{
		List<Department> list = new ArrayList<>();
		if (companyIds!=null && !companyIds.equals("")) {
			String[] ids = companyIds.split(",");
			if (ids!=null && ids.length>0) {
				Integer[] idArray=new Integer[ids.length];
				for (int i = 0; i < idArray.length; i++) {
					idArray[i] = Integer.parseInt(ids[i]);
				}
				list= departmentService.getDepartmentByCompanyIds(idArray, null);
			}
		}
		return list;
	}
	
	/**
	 * 根据公司ID获取部门id/name集合
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDepartmentListMapByCompanyId")
	@ResponseBody
	public List<Map<String, Object>> getDepartmentListMapByCompanyId(Integer companyId,Integer type) throws Exception{
		return departmentService.getDepartmentListMapByCompanyId(companyId,type);
	}
}