package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Contacts;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.db.BaseDao;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月4日
 *
 * version 1.0
 */
public interface ContactsDao extends BaseDao<Contacts> {

	/**
	 * 根据电话号码获取客户ID
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	List<Integer> getCustomerIdByPhone(String hql, String phone) throws Exception;

	/**
	 * 通过电话号码获取
	 * 
	 * @param phone
	 * @param customerId
	 * @return
	 */
	@Deprecated
	Customer getCustomerByPhone(String phone, Integer customerId);

	/**
	 * 通过电话号码在Customer和Contacts中搜索其客户ID
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	Integer getCustomerIdByPhone(String phone) throws Exception;

	/**
	 * 在Customer表中,通过电话号码获取其客户ID
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	Integer getCustomerIdByPhoneAndCId(String phone) throws Exception;

	/**
	 * 修改电话号码时,在Contacts表中,通过电话号码获取其客户ID
	 * 
	 * @param phone
	 * @param contactsId
	 * @return
	 * @throws Exception
	 */
	Integer getCustomerIdByPhoneAndContactsId(String phone, Integer contactsId) throws Exception;

	List<String> getRepeatPhones(List<String> phones) throws Exception;
}
