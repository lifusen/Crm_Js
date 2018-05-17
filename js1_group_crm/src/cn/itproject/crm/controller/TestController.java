package cn.itproject.crm.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Contacts;
import cn.itproject.crm.bean.Customer;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/addCustomer")
	@ResponseBody
	public Boolean addCustomer(Customer customer) {
		System.out.println(customer.getName());
		List<Contacts> contacts = customer.getContacts();
		System.out.println(contacts);
		if (contacts!=null) {
			System.out.println(contacts.size());
			for (Contacts c : contacts) {
				System.out.println(c);
			}
		}
		return true;
	}
}
