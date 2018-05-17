package cn.itproject.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.BankProduct;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.BankProductService;

/**
 * 银行产品控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/bankProduct")
public class BankProductController extends BaseController{
	
	@Resource
	private BankProductService bankProductService;
	
	/**
	 * 获取所有客户来源列表
	 */
	@RequestMapping("/getList.do")
	public String getList(Model model) throws Exception{
		List<BankProduct> bankProducts = bankProductService.getEntityAll();
		model.addAttribute("bankProducts",bankProducts);
		return "page/bankProduct/list";
	}
	
	@RequestMapping("/checkNameIsUnique.do")
	@ResponseBody
	public Boolean checkNameIsUnique(String name) throws Exception{
		System.out.println("checkNameIsUnique..............");
		return !bankProductService.isExist(name);
	}
	
	
	/**
	 * 添加
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Boolean add(BankProduct bankProduct) throws Exception {
		try {
			bankProductService.saveEntity(bankProduct);
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
	public String gotoUpdate(Model model,Integer id) throws Exception {
		BankProduct bankProduct = bankProductService.getEntity(id);
		model.addAttribute("bankProduct", bankProduct);
		return "page/bankProduct/update";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(BankProduct bankProduct) throws Exception {
		try {
			bankProductService.updateEntity(bankProduct);
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
			bankProductService.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
