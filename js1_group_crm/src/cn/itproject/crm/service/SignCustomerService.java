package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 签单客户业务接口
 * @author MrLiu
 *
 */
public interface SignCustomerService extends BaseService<SignCustomer>{
	
	/**
	 * 添加签单客户
	 * @param signCustomer 签单客户对象
	 * @throws Exception
	 */
	public void addSignCustomer(SignCustomer signCustomer,Employee employee) throws Exception;
	
	
	/**
	 * 查询工作提醒列表
	 * @param pageIndex 页码
	 * @param pageSize	也大小
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSignCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds,String keyword,List<Integer> states,String beginDate,String endDate) throws Exception;

	/**
	 * 查询工作提醒列表总行数
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getSignCustomerListCount(List<Integer> employeeIds,
			String keyword,List<Integer> states,String beginDate,String endDate) throws Exception;

	/**
	 * 根据客户ID获取所有签单的客户
	 * @param customerId
	 * @return
	 */
	public List<SignCustomer> getSignCustomerByCustomerId(Integer customerId) throws Exception;

	/**
	 * 修改签单客户
	 * @param signCustomer
	 * @param loginEmployee
	 */
	public void updateSignCustomer(SignCustomer signCustomer,
			Employee loginEmployee) throws Exception;


	/**
	 * 获取客户的所有签单ID
	 * @param cId	客户ID
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getSignCustomerIdsByCId(Integer cId) throws Exception;

	
	/**
	 * 根据客户ID获取签单的客户(分页)
	 * @param pageIndex
	 * @param pageSize
	 * @param customerId
	 * @return
	 */
	public SignCustomer getSignCustomerByCustomerId(Integer pageIndex,
			Integer pageSize, Integer customerId) throws Exception;
	
	/**
	 * 获取签单客户信息
	 * @param customerId
	 * @param signCustomerId
	 * @return
	 */
	public SignCustomer getSignCustomer(Integer customerId,Integer signCustomerId) throws Exception;

	/**
	 * 修改签单客户的放款情况
	 * @param id 签单客户ID
	 * @param string 放款情况
	 */
	public void updateOutLoanCondition(Integer id, String string) throws Exception;
	
	/**
	 * 将抵押物集合转换成JSON字符串
	 * @param collaterals
	 * @return
	 * @throws Exception
	 */
	public String convertCollateralToJSONString(List<String> collaterals) throws Exception;
	/**
	 * 将证件集合转换成JSON字符串
	 * @param certificates
	 * @return
	 * @throws Exception
	 */
	public String convertCertificateToJSONString(List<String> certificates) throws Exception;


	/**
	 * 签单客户维护列表
	 * @param pageIndex
	 * @param pageSize
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMaintainList(Integer pageIndex,
			Integer pageSize, String keyword) throws Exception;


	/**
	 * 签单客户维护数量
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public Integer getMaintainCount(String keyword) throws Exception;


	public void evaluate(String level, Integer sId) throws Exception;
	
	/**
	 * 修改权证信息
	 * @param idArray
	 * @param companyId
	 * @param dId
	 * @param eId
	 * @throws Exception
	 */
	public void updateWarrant(Integer[] idArray,Integer companyId, Integer dId, Integer eId) throws Exception;


	public List<Map<String, Object>> getSignCustomerSummaryInfo(Integer customerId) throws Exception;
}
