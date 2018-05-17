package cn.itproject.crm.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.ContactsService;

/**
 * 客户联系方式控制器
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/contacts")
public class ContactsController extends BaseController{
	
	@Resource
	private ContactsService contactsService;
	
	/**
	 * 
	 * @param phone	电话号码
	 * @param customerId 客户ID
	 * @param contactsId 联系人ID
	 * @param type 操作类型 0:操作客户的手机号码/1:操作联系人的手机号码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getInfoByPhone")
	@ResponseBody
	public String getInfoByPhone(String phone,Integer customerId,Integer contactsId,Integer type) throws Exception{
		if (StringUtils.isNotBlank(phone)) {
			phone = phone.trim();
		}
		Customer customer = contactsService.getCustomerByPhone(phone);
		if (customer!=null) {
			String string = phone+"已存在,该客户属于";
			//获取部门
			Department department = customer.getDepartment();
			String departmentName = null;
			//获取员工
			Employee employee = customer.getEmployee();
			String employeeName = null;
			//获取客户的当前状态
			if (customer.getState()<3) {//0,1,2
				string+=customer.getAddPersonName();
			}else {
				if (department!=null) {
					departmentName = department.getName();
					string += departmentName;
				}
				if (employee!=null) {
					employeeName = employee.getName();
					string += " 客户经理:"+employeeName;
				}
			}
			
			System.out.println("电话号码重复:"+string);
			return string;
		}else {
			return "";
		}
	}
}
