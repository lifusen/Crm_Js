package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.Contacts;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Employee;

/**
 * 客户电话薄业务接口
 * 
 * @author MrLiu
 *
 */
public interface ContactsService extends BaseService<Contacts> {

	/**
	 * 根据电话号码获取其业务员信息
	 * 
	 * @param phone
	 * @return
	 */
	@Deprecated
	List<Employee> getEmployeeByPhone(String phone, Integer customerId) throws Exception;

	public Customer getCustomerByPhone(String phone) throws Exception;

	/**
	 * <code>
	 * 2016-3-13 优化导入客户
	 *  传入电话号码集合,返回在数据库中已存在的电话号码集合
	 * </code>
	 * 
	 * @param phones
	 * @return
	 * @throws Exception
	 */
	public List<String> getRepeatPhones(List<String> phones) throws Exception;
}
