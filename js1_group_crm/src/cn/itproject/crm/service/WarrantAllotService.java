package cn.itproject.crm.service;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.OutLoanBelong;

public interface WarrantAllotService {

	void allot(Employee employee,Integer companyId,Integer dId, Integer eId, String ids) throws Exception;
	
	/**
	 * 添加辅助贷款人员，并跟新签单对应辅贷人员公司、部门、id等信息
	 * @param employeeId
	 * @param departmentId
	 * @param companyId
	 * @param signCustomerId
	 * @return
	 * @throws Exception
	 * 2017年1月13日 上午9:51:13 by SwordLiu
	 */
	boolean addAssistLoaner(OutLoanBelong outLoanBelong) throws Exception;

	/**
	 * 删除指定签单所属的指定贷款人员删除对应的签单权证关系
	 * @param outLoanBelong
	 * @return
	 * 2017年1月13日 下午3:52:37 by SwordLiu
	 */
	boolean removeAssistLoaner(OutLoanBelong outLoanBelong) throws Exception;

}