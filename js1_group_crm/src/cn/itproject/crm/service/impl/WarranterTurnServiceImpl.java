package cn.itproject.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.bean.SignCustomerTurn;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.dao.OutLoanBelongDao;
import cn.itproject.crm.dao.SignCustomerDao;
import cn.itproject.crm.dao.WarranterTurnDao;
import cn.itproject.crm.service.WarranterTurnService;

@Service
public class WarranterTurnServiceImpl implements WarranterTurnService {

	@Resource
	private WarranterTurnDao warranterTurnDao;
	@Resource
	private SignCustomerDao signCustomerDao;
	@Resource
	private EmployeeDao employeeDao;
	@Resource
	private OutLoanBelongDao outLoanBelongDao;
	
	@Override
	public List<Map<String, Object>> getTurnList(Integer pageIndex, Integer pageSize, Integer[] ids, String keyStr)
			throws Exception {
		return warranterTurnDao.getSignCustomersByEId(pageIndex, pageSize, ids, keyStr);
	}

	@Override
	public Integer getTurnCount(Integer[] ids, String keyStr) throws Exception {
		return warranterTurnDao.getCountByEId(ids, keyStr);
	}

	@Override
	public void doTurn(Integer[] sIds, Integer[] eIds, Integer toEmployeeId, String cause, String otherCause)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 修改历史状态
		for (int i = 0; i < sIds.length; i++) {
			Employee oldEmployee = employeeDao.get(eIds[i]);
			Employee newEmployee = employeeDao.get(toEmployeeId);
			SignCustomerTurn signCustomerTurn = new SignCustomerTurn(new Date(), 
										cause, otherCause,new Employee(eIds[i]), 
										new Employee(toEmployeeId), 
										new SignCustomer(sIds[i]));
			warranterTurnDao.save(signCustomerTurn);
			OutLoanBelong outLoanBelong = outLoanBelongDao.getOutLoanBelong(sIds[i], eIds[i]);
			outLoanBelong.setDepartmentId(newEmployee.getDepartment().getId());;
			outLoanBelong.setWarranterId(newEmployee.getId());
			outLoanBelong.setCompanyId(newEmployee.getCompanyId());
			
			outLoanBelong.setTurnDetail("由【"+oldEmployee.getDepartment().getName()+"-"+oldEmployee.getName()+"】"
					+ "移交到【"+newEmployee.getDepartment().getName()+"-"+newEmployee.getName()+"】"+format.format(new Date()));
			outLoanBelong.setCreateTime(new Date());
			outLoanBelongDao.update(outLoanBelong);
		}
	}

	@Override
	public List<Integer> getTurnIds(Integer[] ids, String keyStr) throws Exception {
		return warranterTurnDao.getIdsByEId(ids, keyStr);
	}

	@Override
	public void batchTurn(List<Integer> sIds, Integer eId, Integer toEmployeeId, String cause, String otherCause) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 修改历史状态
		for (int i = 0; i < sIds.size(); i++) {
			Employee oldEmployee = employeeDao.get(eId);
			Employee nowEmployee = employeeDao.get(toEmployeeId);
			SignCustomerTurn signCustomerTurn = new SignCustomerTurn(new Date(), 
										cause, otherCause,new Employee(eId), 
										new Employee(toEmployeeId), 
										new SignCustomer(sIds.get(i)));
			
			warranterTurnDao.save(signCustomerTurn);
			OutLoanBelong outLoanBelong = outLoanBelongDao.getOutLoanBelong(sIds.get(i), eId);
			outLoanBelong.setDepartmentId(nowEmployee.getDepartment().getId());;
			outLoanBelong.setWarranterId(nowEmployee.getId());
			outLoanBelong.setCompanyId(nowEmployee.getCompanyId());
			
			outLoanBelong.setTurnDetail("由【"+oldEmployee.getDepartment().getName()+"-"+oldEmployee.getName()+"】"
					+ "移交到【"+nowEmployee.getDepartment().getName()+"-"+nowEmployee.getName()+"】"+format.format(new Date()));
			outLoanBelong.setCreateTime(new Date());
			outLoanBelongDao.update(outLoanBelong);
		}
	}

}
