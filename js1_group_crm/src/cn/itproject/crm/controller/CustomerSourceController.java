package cn.itproject.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CustomerSourceService;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月6日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/customerSource")
public class CustomerSourceController extends BaseController {

	@Resource
	private CustomerSourceService customerSourceService;

	/**
	 * 获取所有客户来源列表
	 */
	@RequestMapping("/getCustomerSources")
	public String getCustomerSources(Model model) throws Exception {
		List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
		model.addAttribute("customerSources", customerSources);
		return "page/customerSource/customerSourceList";
	}

	/**
	 * 添加
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Boolean add(CustomerSource customerSource) throws Exception {
		try {
			customerSourceService.addCustomerSource(customerSource);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/gotoUpdate")
	public String gotoUpdate(Model model, Integer id) throws Exception {
		CustomerSource customerSource = customerSourceService.getCustomerSource(id);
		model.addAttribute("customerSource", customerSource);
		return "page/customerSource/updateCustomerSource";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(CustomerSource customerSource) throws Exception {
		try {
			customerSourceService.updateCustomerSource(customerSource);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Boolean update(Integer id) throws Exception {
		try {
			customerSourceService.deleteCustomerSource(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping("/getCustomerSourcePie")
	@ResponseBody
	public List<Map<String, Object>> getCustomerSourcePie(String order) throws Exception {
		return customerSourceService.getCustomerSourcePie(order);
	}
}
