package cn.itproject.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.AllotCustomerService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.CustomerSourceService;

/**
 * 添加客户的控制器
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月14日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class AddCustomerController extends BaseController {

	@Resource
	private CustomerService customerService;
	@Resource
	private CustomerSourceService customerSourceService;
	@Resource
	private AllotCustomerService allotCustomerService;

	@RequestMapping("/addCustomerUI")
	public String addCustomerUI(Model model) throws Exception {
		getCustomerSources(model);
		return "page/customer/addBasicInfo";
	}

	/**
	 * 跳转到业务员新增客户页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addCustomerOfSalesmanUI")
	public String addCustomerOfSalesmanUI(Model model) throws Exception {
		getCustomerSources(model);
		return "page/customer/addBasicInfoOfSalesman";
	}

	/**
	 * 获取客户来源
	 * 
	 * @param model
	 * @throws Exception
	 */
	private void getCustomerSources(Model model) throws Exception {
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customerSources", customerSources);
	}

	@RequestMapping("/addCustomerOfSalesman")
	@ResponseBody
	public String addCustomerOfSalesman(Customer customer) throws Exception {
		try {
			// 判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
			Integer num = allotCustomerService.getCustomerIsOverrun(getLoginEmployee().getId(), 1);
			if (num > 0) {
				return num + "";
			} else {
				customerService.addSalesmanCustomer(customer, getLoginEmployee());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
}
