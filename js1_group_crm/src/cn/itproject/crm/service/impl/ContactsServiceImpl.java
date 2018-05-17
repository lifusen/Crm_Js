package cn.itproject.crm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Contacts;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.dao.ContactsDao;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerService;

@Service
public class ContactsServiceImpl extends BaseServiceSupport<Contacts> implements ContactsService {
	@Resource
	private ContactsDao contactsDao;

	@Resource
	private EmployeeDao employeeDao;

	@Resource
	private CustomerService customerService;

	@Override
	protected BaseDao<Contacts> getBaseDao() {
		return contactsDao;
	}

	// public Customer getCustomerByPhone(String phone,Integer
	// customerId,Integer contactsId,Integer type) throws Exception {
	public Customer getCustomerByPhone(String phone) throws Exception {
		Integer cId = contactsDao.getCustomerIdByPhoneAndCId(phone);
		if (cId != null) {
			return customerService.getBasicCustomer(cId);
		}
		return null;
	}

	@Deprecated
	@Override
	public List<Employee> getEmployeeByPhone(String phone, Integer customerId) throws Exception {
		/**
		 * -- 根据电话号码在客户表中查询其客户ID select c.id from customer c where c.phone =
		 * '18898765432'
		 * 
		 * -- 根据电话号码在通讯录中查询客户ID select cs.customerId from contacts cs where
		 * cs.phone = '18756788989'
		 */
		// 客户ID集合
		Set<Integer> customerIds = new HashSet<Integer>();
		// 临时客户ID集合
		List<Integer> tempList = new ArrayList<Integer>();

		// 根据电话号码在客户表中查询其客户ID
		String hql = "select c.id from Customer c where c.phone = :phone";
		tempList = contactsDao.getCustomerIdByPhone(hql, phone);
		System.out.println("customer tempList:" + tempList);
		// 如果有,则存储到customerIds集合中
		if (tempList.size() > 0) {
			customerIds.addAll(tempList);
		}
		System.out.println("customerIds:" + customerIds);

		// 根据电话号码在通讯录中查询客户ID
		hql = "select cs.customer.id from Contacts cs where cs.phone = :phone";
		tempList = contactsDao.getCustomerIdByPhone(hql, phone);
		System.out.println("contacts tempList:" + tempList);
		if (tempList.size() > 0) {
			customerIds.addAll(tempList);
		}
		System.out.println("customerIds:" + customerIds);

		// 排除修改自己的电话号码问题
		if (customerIds.contains(customerId)) { // 如果查询出的电话号码属于该客户的,则比表示为修改,放行
			return null;
		}

		// 根据客户ID获取员工信息集合
		List<Employee> employees = new ArrayList<Employee>();
		for (Integer cId : customerIds) {
			Employee employee = employeeDao.getEmployeeInfoByCustomerId(cId);
			if (employee != null) {
				employees.add(employee);
			}
		}
		return employees;
	}

	@Override
	public List<String> getRepeatPhones(List<String> phones) throws Exception {
		return contactsDao.getRepeatPhones(phones);
	}

}
