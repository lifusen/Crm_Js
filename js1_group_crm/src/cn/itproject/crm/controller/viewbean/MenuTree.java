package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;
import java.util.List;

import cn.itproject.crm.bean.Menu;

/**
 * 模块树
 * @author jianghan
 *
 */
public class MenuTree implements Serializable{
	private static final long serialVersionUID = 2939052283736129453L;
	private Menu menu;
	private List<Menu> menus;
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public MenuTree(){}
	public MenuTree(Menu menu, List<Menu> menus) {
		this.menu = menu;
		this.menus = menus;
	}
}
