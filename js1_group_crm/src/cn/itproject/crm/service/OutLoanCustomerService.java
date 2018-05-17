package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.OutLoanCustomer;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 放款客户业务接口
 * @author MrLiu
 *
 */
public interface OutLoanCustomerService extends BaseService<OutLoanCustomer>{
	
	/**
	 * 添加放款客户
	 * @param outLoanCustomer 放款客户对象
	 * @param warranterId 
	 * @throws Exception
	 */
	public void addOutLoanCustomer(OutLoanCustomer outLoanCustomer,Employee employee, Integer warranterId) throws Exception;
	
	
	/**
	 * 查询工作提醒列表
	 * @param pageIndex 页码
	 * @param pageSize	也大小
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getOutLoanCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds,String keyword,String beginDate,String endDate) throws Exception;

	/**
	 * 查询工作提醒列表总行数
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getOutLoanCustomerListCount(List<Integer> employeeIds,
			String keyword,String beginDate,String endDate) throws Exception;

	/**
	 * 根据客户ID获取所有签单的客户
	 * @param customerId
	 * @return
	 */
	public List<OutLoanCustomer> getOutLoanCustomerByCustomerId(Integer customerId) throws Exception;

	/**
	 * 修改签单客户
	 * @param outLoanCustomer
	 * @param loginEmployee
	 */
	public void updateOutLoanCustomer(OutLoanCustomer outLoanCustomer,
			Employee loginEmployee,Integer customerId) throws Exception;


	/**
	 * 获取客户的所有签单ID
	 * @param cId	客户ID
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getOutLoanCustomerIdsByCId(Integer cId) throws Exception;

	
	/**
	 * 根据客户ID获取所有签单的客户(分页)
	 * @param pageIndex
	 * @param pageSize
	 * @param customerId
	 * @return
	 */
	public List<OutLoanCustomer> getOutLoanCustomerByCustomerId(Integer pageIndex,
			Integer pageSize, Integer customerId) throws Exception;

	/**
	 * 根据签单客户ID，获取该签单下的所有放款概要信息
	 * @param signCustomerId
	 * @return
	 * @throws Exception
	 * 2017年1月5日 上午11:35:38 by SwordLiu
	 */
	public List<Map<String, Object>> getOutLoanCustomerSummaryInfo(Integer signCustomerId) throws Exception;

	/**
	 * 根据放款ID,获取相应的放款信息
	 * @param oId
	 * @return
	 * @throws Exception
	 * 2017年1月6日 下午2:36:53 by SwordLiu
	 */
	public OutLoanCustomer getOutLoanCustomerById(Integer oId) throws Exception;
}
