package cn.itproject.crm.controller.viewbean;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.bean.Customer;

public class ShowCustomer {

	private Customer customer;
	private AllotCustomer allotCustomer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AllotCustomer getAllotCustomer() {
		return allotCustomer;
	}
	public void setAllotCustomer(AllotCustomer allotCustomer) {
		this.allotCustomer = allotCustomer;
	}
}
