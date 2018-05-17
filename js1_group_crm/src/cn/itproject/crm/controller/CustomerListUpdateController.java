package cn.itproject.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.CustomerSourceService;

/**
 * 客户列表双击修改控制器
 * 
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class CustomerListUpdateController extends BaseController {

	@Resource
	private CustomerService customerService;

	@Resource
	private CustomerSourceService customerSourceService;

	/**
	 * 双击列表跳转到客户修改页面 1.基本信息,2.资产,3.辅助信息,4.客户跟进信息 example:
	 * 新订单:customer/gotoUpdateBasicUI.do?id=1&refererUrl:customer/getNewCustomerList.do&isShowFollow:true
	 * 
	 * @param model
	 * @param id
	 *            客户ID
	 * @param refererUrl
	 *            双击列表的路径,点击取消或时,返回到指定的路径
	 * @param menuAttrId
	 *            导航菜单a标签的ID
	 * @param showButton
	 *            显示指定的按钮 sign:显示签单按钮,receive:显示领单按钮
	 * @param isShowFollow
	 *            是否显示客户跟进
	 * @param onlyShowFollowList
	 *            仅仅显示客户跟进列表
	 * @param noShowSaveButton
	 *            不显示保存按钮
	 * @param updateType
	 *            1.修改时不需要进行深层次的表单验证(市场部修改/业务部分配的修改)/2.需要验证(业务员需改需要验证,目前为null)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoUpdateBasicUI")
	public String gotoUpdateBasicUI(Model model, Integer id, String refererUrl, String menuAttrId, String showButton,
			Boolean isShowFollow, Boolean onlyShowFollowList, Boolean noShowSaveButton, Boolean commonPool,
			Integer updateType) throws Exception {
		System.out.println("跳转到客户列表基本信息修改页面.......");
		// 获取客户信息
		Customer customer = customerService.getBasicCustomer(id);
		if (refererUrl != null) {
			refererUrl = refererUrl.replace(",", "&");
		}
		// 客户来源
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customer", customer);
		model.addAttribute("refererUrl", refererUrl);
		model.addAttribute("customerSources", customerSources);
		model.addAttribute("menuAttrId", menuAttrId);
		model.addAttribute("showButton", showButton);
		model.addAttribute("isShowFollow", isShowFollow);
		model.addAttribute("onlyShowFollowList", onlyShowFollowList);
		model.addAttribute("noShowSaveButton", noShowSaveButton);
		model.addAttribute("commonPool", commonPool);
		model.addAttribute("updateCustomerId", id);
		model.addAttribute("updateType", updateType);
		return "page/customerListUpdate/basicListUpdate";
	}

	/**
	 * 双击列表跳转到客户修改页面 1.基本信息,2.资产,3.辅助信息,4.客户跟进信息,5.签单,6.放款 有效客户列表 签单客户列表 放款客户列表
	 * 退单退件客户列表 example:
	 * 
	 * @param model
	 * @param id
	 *            客户ID
	 * @param refererUrl
	 *            双击列表的路径,点击取消或时,返回到指定的路径
	 * @param menuAttrId
	 *            导航菜单a标签的ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoValidListUpdateUI")
	public String gotoValidListUpdateUI(Model model, Integer id, Integer sId, String refererUrl, String menuAttrId,
			String currentListDomId, boolean closeWindow) throws Exception {
		System.out.println("跳转到客户列表修改页面.......");
		// 获取客户信息
		Customer customer = customerService.getBasicCustomer(id);
		// 客户来源
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();

		// 获取到当前客户的资产信息,并构造成抵押物集合保存在session中
		List<CollateralViewBean> customerAssetViewBeans = customerService.buildCollateralViewBean(customer);
		getRequest().getSession().setAttribute("customerAssetViewBeans", customerAssetViewBeans);

		model.addAttribute("customer", customer);
		if (refererUrl != null) {
			refererUrl = refererUrl.replace(",", "&");
		}
		model.addAttribute("refererUrl", refererUrl);
		model.addAttribute("customerSources", customerSources);
		model.addAttribute("showButton", "sign"); // 显示签单按钮
		model.addAttribute("menuAttrId", menuAttrId);
		model.addAttribute("sId", sId);
		model.addAttribute("isUpdatePhone", false);
		model.addAttribute("currentListDomId", currentListDomId); // 当前客户列表的DomId
		model.addAttribute("closeWindow", closeWindow); // 是否关闭当前窗口

		setCustomerSourceCacheMap(model);

		return "page/customerListUpdate/validListUpdate";
	}
}
