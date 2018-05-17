package cn.itproject.crm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Role;
import cn.itproject.crm.dao.RoleDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoSupport<Role> implements RoleDao{

	@Override
	public List<Role> getAll() throws Exception{
		String hql = "from Role where type is null or type !=1";
		return getSession().createQuery(hql).list();
	}

	@Override
	public Integer getCustomerNO(Integer id) throws Exception {
		String sql = "select customerNO from role where id = :id";
		return (Integer) getSession().createSQLQuery(sql).setInteger("id", id).uniqueResult();
	}

	@Override
	public void saveCustomerNO(Integer id, Integer customerNO) throws Exception {
		String sql = "update role set customerNO=:customerNO where id=:id";
		getSession().createSQLQuery(sql)
				.setInteger("customerNO", customerNO)
				.setInteger("id", id)
				.executeUpdate();
	}
}