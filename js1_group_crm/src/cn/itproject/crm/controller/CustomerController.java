package cn.itproject.crm.controller;

import java.util.Date;
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
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.CustomerSourceService;
import cn.itproject.crm.service.LendingInstitutionService;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月7日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	@Resource
	private CustomerService customerService;

	@Resource
	private ContactsService contactsService;

	@Resource
	private CustomerFollowService customerFollowService;

	@Resource
	private LendingInstitutionService lendingInstitutionService;

	@Resource
	private CustomerSourceService customerSourceService;

	@RequestMapping("/getNonAllotCustomers")
	public String getNonAllotCustomers(Model model) throws Exception {
		System.out.println("获取到未分配的所有客户列表");
		List<Customer> customers = customerService.getEntityAll();
		model.addAttribute("customers", customers);
		return "page/customer/xszjAllot";
	}

	/**
	 * 跳转到未分配客户修改基本信息页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/gotoUpdateBasicUI")
	@Deprecated
	public String gotoUpdateBasicUI(Model model, Integer id, String refererUrl) throws Exception {
		System.out.println("获取客户信息并跳转至修改页面");
		// 获取客户信息
		Customer customer = customerService.getBasicCustomer(id);

		model.addAttribute("customer", customer);
		model.addAttribute("refererUrl", refererUrl);
		// 客户来源
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customerSources", customerSources);
		return "page/customer/basicListUpdate";
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/gotoUpdateBasicReceiveUI")
	@Deprecated
	public String gotoUpdateBasicReceiveUI(Model model, Integer id, String refererUrl, Integer hId) throws Exception {
		System.out.println("获取客户信息并跳转至修改页面");
		// 获取客户信息
		Customer customer = customerService.getBasicCustomer(id);

		model.addAttribute("customer", customer);
		model.addAttribute("refererUrl", refererUrl);
		model.addAttribute("hId", hId);
		// 客户来源
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customerSources", customerSources);
		return "page/customer/basicListReceiveUpdate";
	}

	/**
	 * 跳转到包含客户基本信息、签单、客户跟进的修改页面之前获取数据
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/gotoUpdateBasicAndSignAndFollowUI")
	@Deprecated
	public String gotoUpdateBasicAndSignAndFollowUI(Model model, Integer id, String refererUrl, Integer hId)
			throws Exception {
		System.out.println("获取客户信息并跳转至修改页面");
		// 获取客户信息
		Customer customer = customerService.getBasicCustomer(id);

		// List<CollateralViewBean> customerAssetViewBeans =
		// customerService.buildCollateralViewBean(customer);
		// model.addAttribute("customerAssetViewBeans",customerAssetViewBeans);

		List<CollateralViewBean> customerAssetViewBeans = customerService.buildCollateralViewBean(customer);
		// model.addAttribute("customerAssetViewBeans",customerAssetViewBeans);
		getRequest().getSession().setAttribute("customerAssetViewBeans", customerAssetViewBeans);

		model.addAttribute("customer", customer);
		model.addAttribute("refererUrl", refererUrl);
		// 客户来源
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customerSources", customerSources);
		model.addAttribute("hId", hId);
		return "page/customer/validListUpdate";
	}

	/**
	 * 跳转到包含签单和放款的修改页面之前获取数据
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/gotoUpdateSignAndOutLoanUI")
	@Deprecated
	public String gotoUpdateSignAndOutLoanUI(Model model, Integer id, String refererUrl, Integer hId, Integer sId)
			throws Exception {
		System.out.println("获取客户信息并跳转至修改页面");
		// 获取客户信息
		Customer customer = customerService.getBasicCustomer(id);
		List<CollateralViewBean> customerAssetViewBeans = customerService.buildCollateralViewBean(customer);
		// model.addAttribute("customerAssetViewBeans",customerAssetViewBeans);
		getRequest().getSession().setAttribute("customerAssetViewBeans", customerAssetViewBeans);

		model.addAttribute("customer", customer);
		model.addAttribute("refererUrl", refererUrl);
		// 客户来源
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customerSources", customerSources);
		model.addAttribute("hId", hId);
		model.addAttribute("sId", sId);
		return "page/signCustomer/signListUpdate";
	}

	@RequestMapping("/updateCustomer")
	@ResponseBody
	public Integer updateCustomerBasciInfo(Customer customer) throws Exception {
		System.out.println("updateCustomer.............");

		System.out.println(customer);

		Customer updateCustomer = customerService.getEntity(customer.getId());
		if (updateCustomer == null) {
			return 2;
		}

		updateCustomer.setName(customer.getName());
		updateCustomer.setGender(customer.getGender());
		updateCustomer.setPhone(customer.getPhone());
		updateCustomer.setPhoneRemark(customer.getPhoneRemark());
		updateCustomer.setLoanType(customer.getLoanType());
		updateCustomer.setCustomerSource(customer.getCustomerSource());
		updateCustomer.setAge(customer.getAge());
		updateCustomer.setOtherInfo(customer.getOtherInfo());
		updateCustomer.setContacts(customer.getContacts());

		updateCustomer.setHouses(customer.getHouses());
		updateCustomer.setLands(customer.getLands());
		updateCustomer.setCars(customer.getCars());
		updateCustomer.setEnterprises(customer.getEnterprises());
		updateCustomer.setCreditCards(customer.getCreditCards());

		updateCustomer.setMonthIncome(customer.getMonthIncome());
		updateCustomer.setEmbodiment(customer.getEmbodiment());
		updateCustomer.setSocialInsurance(customer.getSocialInsurance());
		updateCustomer.setCredit(customer.getCredit());
		updateCustomer.setEnterpriseNature(customer.getEnterpriseNature());
		updateCustomer.setWorkYear(customer.getWorkYear());
		updateCustomer.setRequiredMoney(customer.getRequiredMoney());
		updateCustomer.setRepaymentLimit(customer.getRepaymentLimit());
		updateCustomer.setUseDate(customer.getUseDate());
		updateCustomer.setLendingInstitution(customer.getLendingInstitution());
		updateCustomer.setCensus(customer.getCensus());
		updateCustomer.setMarriage(customer.getMarriage());
		updateCustomer.setBondExpireDate(customer.getBondExpireDate());
		updateCustomer.setRepaymentType(customer.getRepaymentType());
		updateCustomer.setFundUse(customer.getFundUse());
		updateCustomer.setCustomerCharacter(customer.getCustomerCharacter());
		updateCustomer.setAmountliabilitys(customer.getAmountliabilitys());
		updateCustomer.setDebtTotal(customer.getDebtTotal());

		updateCustomer.setUpdateTime(new Date());
		// 客户状态不设置
		// 客户跟进不设置
		// 客户等级不设置
		// 客户关注等级不设置
		// 客户资料完成比不设置
		// 签单客户集合不设置
		// 可见性不设置
		try {
			// 修改客户基本信息
			customerService.updateCustomer(updateCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	@RequestMapping("/updateCustomerLevel")
	@ResponseBody
	public Boolean updateCustomerLevel(Integer id, String customerLevel) throws Exception {
		try {
			customerService.updateCustomerLevel(id, customerLevel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping("/updateAttentionLevel")
	@ResponseBody
	public Boolean updateAttentionLevel(Integer id, String attentionLevel) throws Exception {
		try {
			customerService.updateAttentionLevel(id, attentionLevel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping("/updateLastTime")
	@ResponseBody
	public Boolean updateLastTime(Integer id) throws Exception {
		customerService.updateLastTime(id);
		return true;
	}
}
