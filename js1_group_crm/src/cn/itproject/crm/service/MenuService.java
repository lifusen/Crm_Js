package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.Menu;

public interface MenuService extends BaseService<Menu>{

	/**
	 * 根据菜单Id获取菜单列表
	 * @param menusIds	菜单ID
	 * @return
	 * @throws Exception
	 */
	List<Menu> getMenuByIds(Integer[] menusIds) throws Exception;

	/**
	 * 根据菜单Id获取父节点菜单
	 * @param menusIds	菜单ID
	 * @return
	 * @throws Exception
	 */
	List<Menu> getParentMenus(Integer[] menusIds) throws Exception;

	/**
	 * 根据菜单Id获取叶子节点菜单
	 * @param menusIds 菜单ID
	 * @return
	 * @throws Exception
	 */
	List<Menu> getLeafMenu(Integer[] menusIds) throws Exception;

}