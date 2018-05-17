package cn.itproject.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.CustomerTurn;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.dao.CustomerListDao;
import cn.itproject.crm.dao.CustomerTurnDao;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.service.CustomerTurnService;
import cn.itproject.crm.util.Constant;

@Service
public class CustomerTurnServiceImpl implements CustomerTurnService{

	@Resource
	private CustomerListDao customerListDao;
	
	@Resource
	CustomerTurnDao customerTurnDao;
	@Resource
	CustomerDao customerDao;
	@Resource
	EmployeeDao employeeDao;
	
	@Override
	public List<Map<String, Object>> getTurnList(Integer pageIndex,
			Integer pageSize, Integer[] ids, String keyStr,String fLevel,String cLevel) throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(CustomerState.allocatedToEmp.ordinal());
		list.add(CustomerState.follow.ordinal());
		list.add(CustomerState.sign.ordinal());
		list.add(CustomerState.outLoan.ordinal());
		list.add(CustomerState.chargeback.ordinal());
		list.add(CustomerState.reject.ordinal());
		return customerListDao.getCustomersByStateAndEId(pageIndex, pageSize, ids, list, keyStr,fLevel,cLevel);
	}

	@Override
	public Integer getTurnCount(Integer[] ids, String keyStr,String fLevel,String cLevel) throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(CustomerState.allocatedToEmp.ordinal());
		list.add(CustomerState.follow.ordinal());
		list.add(CustomerState.sign.ordinal());
		list.add(CustomerState.outLoan.ordinal());
		list.add(CustomerState.chargeback.ordinal());
		list.add(CustomerState.reject.ordinal());
		return customerListDao.getCountByteStateAndEId(list, ids, keyStr,fLevel,cLevel);
	}

	@Override
	public void doTurn(Integer[] cIds, Integer[] eIds, Integer toEmployeeId,
			String cause, String otherCause) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 修改历史状态
		for (int i = 0; i < cIds.length; i++) {
			Employee oldEmployee = employeeDao.get(eIds[i]);
			Employee nowEmployee = employeeDao.get(toEmployeeId);
			CustomerTurn customerTurn = new CustomerTurn(new Date(), 
										cause, otherCause,new Employee(eIds[i]), 
										new Employee(toEmployeeId), 
										new Customer(cIds[0]));
			customerTurnDao.save(customerTurn);
			Customer customer = customerDao.get(cIds[i]);
			customer.setDepartment(nowEmployee.getDepartment());
			customer.setEmployee(nowEmployee);
			customer.setCompanyId(nowEmployee.getCompanyId());
			
			customer.setTurnDetail("由【"+oldEmployee.getDepartment().getName()+"-"+oldEmployee.getName()+"】"
					+ "移交到【"+nowEmployee.getDepartment().getName()+"-"+nowEmployee.getName()+"】"+format.format(new Date()));
			customerDao.update(customer);
		}
	}

	@Override
	public List<Integer> getTurnIds(Integer[] ids, String keyStr, 
			String fLevel, String cLevel) throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(CustomerState.allocatedToEmp.ordinal());
		list.add(CustomerState.follow.ordinal());
		list.add(CustomerState.sign.ordinal());
		list.add(CustomerState.outLoan.ordinal());
		list.add(CustomerState.chargeback.ordinal());
		list.add(CustomerState.reject.ordinal());
		return customerListDao.getIdsByteStateAndEId(list, ids, keyStr,fLevel,cLevel);
	}

	@Override
	public void batchTurn(List<Integer> cIds, Integer eId, Integer toEmployeeId, String cause, String otherCause) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 修改历史状态
		for (int i = 0; i < cIds.size(); i++) {
			Employee oldEmployee = employeeDao.get(eId);
			Employee nowEmployee = employeeDao.get(toEmployeeId);
			CustomerTurn customerTurn = new CustomerTurn(new Date(), 
										cause, otherCause,new Employee(eId), 
										new Employee(toEmployeeId), 
										new Customer(eId));
			customerTurnDao.save(customerTurn);
			Customer customer = customerDao.get(cIds.get(i));
			customer.setDepartment(nowEmployee.getDepartment());
			customer.setEmployee(nowEmployee);
			customer.setTurnDetail("由【"+oldEmployee.getDepartment().getName()+"-"+oldEmployee.getName()+"】"
					+ "移交到【"+nowEmployee.getDepartment().getName()+"-"+nowEmployee.getName()+"】"+format.format(new Date()));
			customerDao.update(customer);
	}
	}
}
