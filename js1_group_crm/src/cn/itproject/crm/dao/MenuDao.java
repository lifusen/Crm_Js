package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Menu;
import cn.itproject.crm.db.BaseDao;

public interface MenuDao extends BaseDao<Menu>{

	List<Menu> queryList(Integer[] menusIds) throws Exception;

	List<Menu> findParentMenus(Integer[] menusIds) throws Exception;

	List<Menu> findLeafMenus(Integer[] menusIds) throws Exception;

}
