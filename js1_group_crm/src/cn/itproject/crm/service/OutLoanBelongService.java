package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.OutLoanBelong;

public interface OutLoanBelongService extends BaseService<OutLoanBelong>{
	
	/**
	 * 根据签单客户ID和员工ID，获取当前签单是否为该权证主贷
	 * @param signCustomerId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 * 2017年1月12日 下午4:39:05 by SwordLiu
	 */
	public Integer getType(Integer signCustomerId, Integer employeeId) throws Exception;
	
	public void addOutLoanBelong(OutLoanBelong outLoanBelong) throws Exception;

	/**
	 * 获取指定签单下的所有权证人员名单
	 * @param sId
	 * @return
	 * 2017年1月13日 下午12:45:50 by SwordLiu
	 */
	public List<OutLoanBelong> getOutLoanBelong(Integer sId) throws Exception;

}
