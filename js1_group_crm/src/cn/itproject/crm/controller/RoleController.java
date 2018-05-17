package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Menu;
import cn.itproject.crm.bean.Role;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.MenuTree;
import cn.itproject.crm.service.MenuService;
import cn.itproject.crm.service.RoleService;

/**
 * Role控制器
 * @author jianghan
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleController extends BaseController{

	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;
	/**
	 * 分页获取角色列表
	 * @param model
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/roleList")
	public String roleList(Model model,Integer pageIndex,Integer pageSize) throws Exception{
		// TODO 分页
		if (pageSize==null || pageSize<=0) {
			pageSize=10;
		}
		if (pageIndex==null || pageIndex<=0) {
			pageIndex=1;
		}
		model.addAttribute("roles", roleService.getRoleList());
		return "/page/role/roleList";
	}
	
	@RequestMapping("/gotoAdd")
	public String gotoAdd(Model model) throws Exception{
		List<MenuTree> menuTrees = new ArrayList<MenuTree>();
		List<Menu> parentMenus = menuService.getParentMenus(null);
		for (Menu pm:parentMenus) {
			MenuTree menuTree = new MenuTree(pm,new ArrayList<Menu>());
			menuTrees.add(menuTree);
		}
		List<Menu> leafMenus = menuService.getLeafMenu(null);
		for (Menu lm:leafMenus) {
			for (MenuTree tree:menuTrees) {
				if (lm.getParent().equals(tree.getMenu())) {
					tree.getMenus().add(lm);
				}
			}
		}
		model.addAttribute("menuTrees", menuTrees);
		return "page/role/addRole";
	}
	/**
	 * 添加角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public Boolean addRole(Role role) throws Exception{
		try {
			if (role==null) {
				return false;
			}
			role.setReadOnly(1);
			roleService.addEntity(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取修改的角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoUpdate")
	public String getRole(Integer id,Model model) throws Exception{
		Role role = roleService.getEntity(id);
		String menus = role.getMenus();
		String[] menuArray = menus.split("-");
		List<Integer> menusIds = new ArrayList<>();
		for (int i = 0; i < menuArray.length; i++) {
			menusIds.add(Integer.parseInt(menuArray[i]));
		}
		model.addAttribute("role", role);
		model.addAttribute("checkedMenus",menusIds);
		
		List<MenuTree> menuTrees = new ArrayList<MenuTree>();
		List<Menu> parentMenus = menuService.getParentMenus(null);
		for (Menu pm:parentMenus) {
			MenuTree menuTree = new MenuTree(pm,new ArrayList<Menu>());
			menuTrees.add(menuTree);
		}
		List<Menu> leafMenus = menuService.getLeafMenu(null);
		for (Menu lm:leafMenus) {
			for (MenuTree tree:menuTrees) {
				if (lm.getParent().equals(tree.getMenu())) {
					tree.getMenus().add(lm);
				}
			}
		}
		model.addAttribute("menuTrees", menuTrees);
		return "page/role/updateRole";
	}
	/**
	 * 修改角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public Boolean updateRole(Role role){
		try {
			roleService.updateEntity(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 删除
	 * @param roleIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Boolean deleteRole(Integer id){
		try {
			roleService.deleteEntity(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@RequestMapping("/getRoleCustomerNO")
	@ResponseBody
	public Integer getRoleCustomerNO(Integer id){
		try {
			return roleService.getCustomerNO(id);
		} catch (Exception e) {
			return 0;
		}
	}
	
	@RequestMapping("/saveRoleCustomerNO")
	@ResponseBody
	public boolean saveRoleCustomerNO(Integer id, Integer customerNO){
		try {
			roleService.saveCustomerNO(id, customerNO);
			System.out.println("成功");
			return true;
		} catch (Exception e) {
			System.out.println("出错了 ？");
			return false;
		}
	}
}