package cn.itproject.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.AllotCustomer.AllotType;
import cn.itproject.crm.bean.Customer.CustomerAddType;
import cn.itproject.crm.bean.CustomerRoster.RosterState;
import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Role;
import cn.itproject.crm.dao.AllotCustomerDao;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.dao.CustomerRosterDao;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.AllotCustomerService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;

@Service("allotCustomerService")
public class AllotCustomerServiceImpl extends BaseServiceSupport<AllotCustomer> implements AllotCustomerService {

	@Resource
	AllotCustomerDao allotCustomerDao;
	@Resource
	CustomerService customerService;
	@Resource
	EmployeeDao employeeDao;
	@Resource
	CustomerRosterDao customerRosterDao;
	@Resource
	CustomerDao customerDao;
	@Resource
	EmployeeService employeeService;

	@Override
	protected BaseDao<AllotCustomer> getBaseDao() {
		return allotCustomerDao;
	}

	@Override
	public void updateByCId(Integer cId, Integer eId) throws Exception {
		allotCustomerDao.updateStaff(cId, eId);
	}

	@Override
	public void allotToDepartment(AllotCustomer allotCustomer) throws Exception {
		addEntity(allotCustomer);
		// 修改客户状态
		Customer customer = customerDao.get(allotCustomer.getCustomer().getId());
		customer.setUpdateTime(new Date());
		if (allotCustomer.getCompanyId() != null) {
			customer.setCompanyId(allotCustomer.getCompanyId());
		}
		if (allotCustomer.getDepartment() != null) {
			customer.setDepartment(allotCustomer.getDepartment());
			customer.setState(CustomerState.allocatedToDept.ordinal());
		}
		customerDao.update(customer);
	}

	@Override
	public void allotToEmployee(Integer cId, Integer eId, Employee nowEmployee) throws Exception {
		Employee allotEmployee = employeeDao.get(eId);
		//
		AllotCustomer allotCustomer = new AllotCustomer();
		Customer customer = customerDao.get(cId);
		Department department = allotEmployee.getDepartment();

		allotCustomer.setEmployee(allotEmployee);
		allotCustomer.setCustomer(customer);
		allotCustomer.setDepartment(department);
		allotCustomer.setAllotType(AllotType.TO_EMPLOYEE.ordinal());
		allotCustomer.setOperation(nowEmployee);
		allotCustomerDao.save(allotCustomer);
		customer.setEmployee(allotEmployee);
		customer.setDepartment(allotEmployee.getDepartment());
		customer.setState(CustomerState.allocatedToEmp.ordinal());
		customer.setUpdateTime(new Date());
		customerDao.update(customer);
	}

	@Override
	public void batchAllotToDepartment(Integer companyId, Integer dId, Integer[] idArrayInt, Employee nowEmployee)
			throws Exception {
		for (int i = 0; i < idArrayInt.length; i++) {

			AllotCustomer allotCustomer = new AllotCustomer();
			Customer customer = new Customer();
			customer.setId(idArrayInt[i]);
			Department department = new Department();
			department.setId(dId);

			allotCustomer.setCustomer(customer);
			allotCustomer.setCompanyId(companyId);
			allotCustomer.setDepartment(department);
			allotCustomer.setAllotType(AllotType.TO_DEPARTMENT.ordinal());
			allotCustomer.setOperation(nowEmployee);

			allotToDepartment(allotCustomer);
		}
	}

	@Override
	public void batchAllotToEmployee(Integer eId, Integer[] idArrayInt, Employee nowEmployee) throws Exception {
		for (int i = 0; i < idArrayInt.length; i++) {
			allotToEmployee(idArrayInt[i], eId, nowEmployee);
		}
	}

	@Override
	public void batchAllotToDepartment(Integer companyId, Integer dId, List<Customer> customers, Employee nowEmployee,
			Integer rId) throws Exception {
		List<Integer> ids = new ArrayList<>();
		Department department = new Department();
		department.setId(dId);
		for (Customer c : customers) {
			AllotCustomer allotCustomer = new AllotCustomer();
			allotCustomer.setCustomer(c);
			allotCustomer.setDepartment(department);
			allotCustomer.setCompanyId(companyId);
			allotCustomer.setAllotType(AllotType.TO_DEPARTMENT.ordinal());
			allotCustomer.setOperation(nowEmployee);
			allotCustomerDao.save(allotCustomer);
			ids.add(allotCustomer.getCustomer().getId());
		}
		System.out.println(dId);
		System.out.println(ids);
		// 批量修改
		customerDao.batchUpdate(companyId, dId, ids, CustomerState.allocatedToDept.ordinal());
		Integer rosterCount = customerService.getcountByAddType(CustomerAddType.excel, rId);
		if (rosterCount < 1) {
			customerRosterDao.updateState(RosterState.old, rId);
		}
	}

	@Override
	public List<Map<String, Object>> getAllotToDepartmentList(Integer pageIndex, Integer pageSize, Integer id,
			String keyStr) throws Exception {

		return allotCustomerDao.getAllotToDepartList(pageIndex, pageSize, Constant.allotToDepartments, id, keyStr);
	}

	@Override
	public Integer getAllotToDepartmentCount(Integer id, String keyStr) throws Exception {
		return allotCustomerDao.getAllotToDepartCount(Constant.allotToDepartments, id, keyStr);
	}

	@Override
	public List<Map<String, Object>> getAllotToEmployeeList(Integer dId, Integer pageIndex, Integer pageSize,
			String keyStr, Integer companyId, Integer departmentId) throws Exception {
		return allotCustomerDao.getAllotToEmplList(pageIndex, pageSize, Constant.allotToEmployees, dId, keyStr,
				companyId, departmentId);
	}

	@Override
	public Integer getAllotToEmployeeCount(Integer dId, String keyStr, Integer companyId, Integer departmentId)
			throws Exception {
		return allotCustomerDao.getAllotToEmplCount(Constant.allotToEmployees, dId, keyStr, companyId, departmentId);
	}

	@Override
	public List<Map<String, Object>> getAssignedOrderList(Integer pageIndex, Integer pageSize, Integer id,
			String keyStr) throws Exception {
		return allotCustomerDao.getAssignedOrderList(pageIndex, pageSize, id, keyStr);
	}

	@Override
	public Integer getAssignedCount(Integer id, String keyStr) throws Exception {
		return allotCustomerDao.getAssignedCount(id, keyStr);
	}

	@Override
	public List<Map<String, Object>> getReceiveOrderList(Integer pageIndex, Integer pageSize, Integer[] ids,
			String keyStr) throws Exception {
		return allotCustomerDao.getReceiveOrderList(pageIndex, pageSize, ids, keyStr);
	}

	@Override
	public Integer getReceiveOrderCount(Integer[] ids, String keyStr) throws Exception {
		return allotCustomerDao.getReceiveOrderCount(ids, keyStr);
	}

	@Override
	public Integer getCustomerIsOverrun(Integer employeeId, Integer customerCount) throws Exception {
		// 判断员工持有客户在接受此次分配后，是否超出持有客户数量上限
		Employee emp = employeeService.getEmployee(employeeId);
		Integer customerCountOfEmployee = customerService.getValidCustomerCount(employeeId);
		// 超出数量上限的数量
		Integer num = 0;
		System.out.println(emp.getCustomerNO()+"jhaksjdhkjahs");
		if (emp.getCustomerNO() > 0) {
			num = customerCountOfEmployee + customerCount - emp.getCustomerNO();
		} else if (emp.getRole().getCustomerNO() > 0) {
			num = customerCountOfEmployee + customerCount - emp.getRole().getCustomerNO();
		}
		System.out.println("<=====当前员工客户数量上限：" + emp.getCustomerNO() + ",当前员工角色客户数量上限：" + emp.getRole().getCustomerNO()
				+ ",当前员工拥有客户:" + customerCountOfEmployee + ",超过上限数量：" + num + ",移交客户数量：" + customerCount + "=====>");

		return num;
	}
}
