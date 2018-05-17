package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Menu;
import cn.itproject.crm.dao.MenuDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.MenuService;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceSupport<Menu> implements MenuService{

	@Resource
	private MenuDao menuDao;
	
	@Override
	protected BaseDao<Menu> getBaseDao() {
		return menuDao;
	}

	@Override
	public List<Menu> getMenuByIds(Integer[] menusIds) throws Exception {
		return menuDao.queryList(menusIds);
	}

	@Override
	public List<Menu> getParentMenus(Integer[] menusIds) throws Exception {
		return menuDao.findParentMenus(menusIds);
	}

	@Override
	public List<Menu> getLeafMenu(Integer[] menusIds) throws Exception {
		return menuDao.findLeafMenus(menusIds);
	}
}
