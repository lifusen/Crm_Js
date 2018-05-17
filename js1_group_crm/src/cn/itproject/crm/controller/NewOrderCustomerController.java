package cn.itproject.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.ShowCustomer;
import cn.itproject.crm.service.AllotCustomerService;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.NewOrderService;
import cn.itproject.crm.util.Constant;

/**
 * 我的新订单
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class NewOrderCustomerController extends BaseController{
	
	@Resource
	private CustomerService customerService;
	@Resource
	private ContactsService contactsService;
	@Resource
	private CustomerFollowService customerFollowService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private AllotCustomerService allotCustomerService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private NewOrderService newOrderService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("getNewCustomerList")
	public String getNewCustomerList(Integer pageIndex,Integer pageSize,Model model,Integer initPage,String keyStr) throws Exception{
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		keyStr = (keyStr==null?"":keyStr);
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		List<Map<String, Object>> customers = null;
		Integer count = null;
		String type = employee.getType();
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			model.addAttribute("companys", companyService.getAllCompany(1));
		}
		
		if ((StringUtils.isNotBlank(type) && type.equals(Constant.SUPER_ROLE))) {
			customers = newOrderService.getNewOrderList(pageIndex, pageSize,null,keyStr);
			count = newOrderService.getNewOrderCount(null,keyStr);
		} else if (Constant.managerRoleIds.contains(roleId)) {
			customers = newOrderService.getNewOrderList(pageIndex, pageSize,null,keyStr);
			count = newOrderService.getNewOrderCount(null,keyStr);
		}else if(roleId==3){
			List<Employee> employees = employeeService.getEmployeeByDId(employee.getId(), employee.getDepartment().getId());
			if (employees!=null && employees.size()>0) {
				Integer[] ids = new Integer[employees.size()];
				for (int i=0;i<ids.length;i++) {
					ids[i] = employees.get(i).getId();
				}
				customers = newOrderService.getNewOrderList(pageIndex, pageSize,ids,keyStr);
				count = newOrderService.getNewOrderCount(ids,keyStr);
			}
		}else {
			Integer[] ids = new Integer[1];
			ids[0] = employee.getId();
			customers = newOrderService.getNewOrderList(pageIndex, pageSize,ids,keyStr);
			count = newOrderService.getNewOrderCount(ids,keyStr);
		}
		
		builderParam(model, pageIndex, pageSize, count, 
				"customer/getNewCustomerList.do?initPage=1&keyStr="+keyStr, "tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("keyStr", keyStr);
		if (initPage==null) {
			return "page/customer/newOrder";
		}
		return "page/customer/newOrder-List";
	}
}
