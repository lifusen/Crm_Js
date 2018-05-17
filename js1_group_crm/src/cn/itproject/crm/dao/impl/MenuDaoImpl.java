package cn.itproject.crm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Menu;
import cn.itproject.crm.dao.CompanyDao;
import cn.itproject.crm.dao.MenuDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoSupport<Menu> implements MenuDao {

	@Resource
	private CompanyDao companyDao;

	@Override
	public List<Menu> queryList(Integer[] menusIds) throws Exception {
		String hql = "from Menu where id in(:id)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("id", menusIds);
		return query.list();
	}

	@Override
	public List<Menu> findParentMenus(Integer[] menusIds) throws Exception {
		String type = "";
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			Company company = companyDao.get(LoginUserUtil.getCompanyId());
			/*
			 * if (company.getType()==2) { // 权证中心 type = " and type = 2 ";
			 * }else {
			 */
			type = " and type is null ";
			// }
		}
		Query query = null;
		if (menusIds != null) {
			String hql = "from Menu where id in(:id) and parent=null " + type + " order by showOrder";
			query = getSession().createQuery(hql);
			query.setParameterList("id", menusIds);
		} else {
			String hql = "from Menu where parent=null " + type + " order by showOrder";
			query = getSession().createQuery(hql);
		}
		return query.list();
	}

	@Override
	public List<Menu> findLeafMenus(Integer[] menusIds) throws Exception {
		Query query = null;
		String superRole = "";
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			Company company = companyDao.get(LoginUserUtil.getCompanyId());
			/*
			 * if (company.getType()==2) { // 权证中心 superRole = " and type = 2 ";
			 * }else {
			 */
			superRole = " and type is null ";
			// }
		}

		if (menusIds != null) {
			String hql = "from Menu where id in(:id) and parent!=null " + superRole + " order by showOrder";
			query = getSession().createQuery(hql);
			query.setParameterList("id", menusIds);
		} else {
			String hql = "from Menu where parent!=null " + superRole + " order by showOrder";
			query = getSession().createQuery(hql);
		}
		return query.list();
	}
}