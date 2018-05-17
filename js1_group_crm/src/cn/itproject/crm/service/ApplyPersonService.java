package cn.itproject.crm.service;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.bean.Employee;

public interface ApplyPersonService extends BaseService<ApplyPerson> {

	void addHrPerson(ApplyPerson applyPerson, Employee employee) throws Exception;

	String getPersonById(Integer id) throws Exception;

	String getPersonNameById(Integer id) throws Exception;

}
