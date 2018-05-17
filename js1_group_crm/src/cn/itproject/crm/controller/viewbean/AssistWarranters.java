package cn.itproject.crm.controller.viewbean;

import java.util.List;

import cn.itproject.crm.bean.Employee;

public class AssistWarranters {
	private List<Employee> employees;

	public AssistWarranters() {
	}

	public AssistWarranters(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
