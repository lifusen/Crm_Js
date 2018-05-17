package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.LendingInstitution;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.LendingInstitutionService;

/**
 * 贷款机构控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/lendingInstitution")
public class LendingInstitutionController extends BaseController{
	
	@Resource
	private LendingInstitutionService lendingInstitutionService;
	
	@RequestMapping("/getAllLendingInstitution")
	@ResponseBody
	public List<LendingInstitution> getAllLendingInstitution() throws Exception {
		System.out.println("getAllLendingInstitution.............");
		List<LendingInstitution> lendingInstitutions = lendingInstitutionService.getEntityAll();
		return lendingInstitutions;
	}
	
	/**
	 * 获取所有的贷款机构
	 */
	@RequestMapping("/getList")
	public String getList(Model model) throws Exception{
		List<LendingInstitution> lendingInstitutions = lendingInstitutionService.getEntityAll();
		
		model.addAttribute("lendingInstitutions",lendingInstitutions);
		return "page/lendingInstitution/list";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Boolean add(LendingInstitution lendingInstitution) throws Exception {
		try {
			lendingInstitutionService.addEntity(lendingInstitution);
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
		LendingInstitution lendingInstitution = lendingInstitutionService.getEntity(id);
		model.addAttribute("lendingInstitution", lendingInstitution);
		return "page/lendingInstitution/update";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(LendingInstitution lendingInstitution) throws Exception {
		try {
			lendingInstitutionService.updateEntity(lendingInstitution);
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
			lendingInstitutionService.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
