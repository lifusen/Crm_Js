package cn.itproject.crm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.DepartmentService;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController{

	@Resource
	private CompanyService companyService;
	@Resource
	private DepartmentService departmentService;
	
	@RequestMapping("/getCompanys")
	public String getCompanys(Integer pageIndex,Integer pageSize,Model model,Integer initPage) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		List<Company> companys = companyService.getCompanyList(pageIndex,pageSize);
		model.addAttribute("companys", companys);
		Integer count = companyService.getCompanyCount();
		builderParam(model, pageIndex, pageSize, count, 
				"company/getCompanys.do?initPage=1", "tableList");
		if (initPage==null) {
			return "page/company/company";
		}else {
			return "page/company/company-List";
		}
	}

	@RequestMapping("/getCompanyListByType")
	@ResponseBody
	public List<Company> getCompanyListByType(Integer type) throws Exception{
		if (type == null) {
			type = 1;	// 默认为业务公司 
		}
		return companyService.getCompanyByType(type);
	}
	
	
	@RequestMapping("/gotoAdd")
	public String gotoAdd(Model model) throws Exception {
		return "page/company/addCompany";
	}
	
	@RequestMapping("/gotoUpdate")
	public String gotoUpdate(Model model,Integer id) throws Exception {
		Company company = companyService.getCompany(id);
		getRequest().getSession().setAttribute("updateCompany", company);
		model.addAttribute("company", company);
		return "page/company/updateCompany";
	}
	
	@RequestMapping(value="addCompany",method=RequestMethod.POST)
	@ResponseBody
	public Boolean addCompany(Company company){
		try {
			if (company==null) {
				return false;
			}
			Employee employee = (Employee) getRequest().getSession().getAttribute("loginUser");
			company.setCreater(employee.getId());
			company.setCreaterName(employee.getName());
			company.setCreateTime(new Date());
			companyService.addCompany(company);
			if (company.getType()==1) {
				// 添加部门
				Department department = new Department();
				department.setName("市场部");
				department.setCompanyId(company.getId());
				department.setRemark("默认部门");
				departmentService.addDepartment(department);
			}else {
				// 添加部门
				Department department = new Department();
				department.setName("权证管理部");
				department.setCompanyId(company.getId());
				department.setRemark("默认部门");
				departmentService.addDepartment(department);
			}
			// 刷新application中的所有公司信息
			refreshCompany();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="updateCompany",method=RequestMethod.POST)
	@ResponseBody
	public Boolean updateCompany(Company company){
		try {
			Company nowCompany = (Company) getRequest().getSession().getAttribute("updateCompany");
			Employee employee = (Employee) getRequest().getSession().getAttribute("loginUser");
			company.setId(nowCompany.getId());
			company.setUpdateTime(new Date());
			company.setEditor(employee.getId());
			company.setEditorName(employee.getName());
			companyService.updateCompany(company);
			// 刷新application中的所有公司信息
			refreshCompany();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping("/refreshCompany")
	@ResponseBody
	public String refreshCompany() {
		try{
			// 获取所有公司信息
			List<Company> companies = companyService.getAllCompany(null);
			// 存储所有公司的id-name
			Map<Integer, String> allCompanyNameMap = new HashMap<Integer,String>();
			// 存储所有公司的id-company对象
			Map<Integer, Company> allCompanyMap = new HashMap<Integer,Company>();
			if (companies!=null) {
				for (Company company : companies) {
					Integer id = company.getId();
					String name = company.getName();
					allCompanyNameMap.put(id,name); //id-name
					allCompanyMap.put(id, company); //id-company
				}
			}
			// 获取application对象
			ServletContext application = getRequest().getSession().getServletContext();
			// 将所有公司的list存储到application中
			application.setAttribute("allCompanyList", companies);
			// 将所有公司的id-name存储到application中
			application.setAttribute("allCompanyNameMap", allCompanyNameMap);
			// 将所有公司的id-company存储到application中
			application.setAttribute("allCompanyMap", allCompanyMap);
			System.out.println("\n刷新application中的所有公司信息成功!");
			System.out.println(companies+"\n");
			return "刷新application中的所有公司信息成功!";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "刷新application中的所有公司信息失败!";
	}
}
