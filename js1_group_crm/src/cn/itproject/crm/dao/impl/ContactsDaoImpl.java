package cn.itproject.crm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Contacts;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.dao.ContactsDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月14日
 *
 *       version 1.0
 */
@Repository
public class ContactsDaoImpl extends BaseDaoSupport<Contacts> implements ContactsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCustomerIdByPhone(String hql, String phone) throws Exception {
		return getSession().createQuery(hql).setString("phone", phone).list();
	}

	@Deprecated
	@Override
	public Customer getCustomerByPhone(String phone, Integer customerId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCustomerIdByPhone(String phone) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select c.id from customer c");
		sql.append(" left join contacts cs");
		sql.append(" on c.id = cs.customerId");
		sql.append(" where c.phone = :phone or cs.phone = :phone");
		List<Integer> ids = getSession().createSQLQuery(sql.toString()).setString("phone", phone).list();
		if (ids != null && ids.size() > 0) {
			return ids.get(0);
		}
		return null;
	}

	@Override
	public Integer getCustomerIdByPhoneAndCId(String phone) throws Exception {
		String sql = "select c.id from customer c where c.phone = :phone";
		Query query = getSession().createSQLQuery(sql);
		query.setString("phone", phone);
		return (Integer) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCustomerIdByPhoneAndContactsId(String phone, Integer contactsId) throws Exception {
		String sql = "select cs.customerId from contacts cs where cs.phone = :phone";
		if (contactsId != null) {
			sql += " and cs.id <> :contactsId";
		}
		Query query = getSession().createSQLQuery(sql);
		query.setString("phone", phone);
		if (contactsId != null) {
			query.setInteger("contactsId", contactsId);
		}
		List<Integer> ids = query.list();
		if (ids != null && ids.size() > 0) {
			return ids.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRepeatPhones(List<String> phones) throws Exception {
		if (phones == null || phones.size() == 0) {
			return null;
		}
		String sql = "select phone from customer where phone in(:phoneList)";
		Query query = getSession().createSQLQuery(sql);
		query.setParameterList("phoneList", phones);
		return query.list();
	}
}
