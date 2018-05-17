package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.OutLoanCustomer;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 放款客户DAO接口
 * @author MrLiu
 *
 */
public interface OutLoanCustomerDao extends BaseDao<OutLoanCustomer>{
	/**
	 * 查询工作提醒列表
	 * @param pageIndex 页码
	 * @param pageSize	也大小
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @param orderColumn 排序列
	 * @param orderByType 排序方式
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getOutLoanCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds,String keyword,String orderColumn,OrderByType orderByType, Integer type) throws Exception;
	
	/**
	 * 查询工作提醒列表总行数
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getOutLoanCustomerListCount(List<Integer> employeeIds,
			String keyword, Integer type) throws Exception;

	/**
	 * 获取某个客户的所有签单ID
	 * @param cId
	 * @return
	 */
	public List<Integer> getOutLoanCustomerIdsByCId(Integer cId) throws Exception;

	/**
	 * 通过签单客户ID获取放款客户
	 * @param sId
	 * @return
	 */
	public OutLoanCustomer getOutloanCustomerBySId(Integer sId) throws Exception;

	/**
	 * 根据签单客户ID，查询当前签单客户下的所有放款概要信息
	 * @param signCustomerId
	 * @return
	 * @throws Exception
	 * 2017年1月5日 上午11:50:00 by SwordLiu
	 */
	public List<Map<String, Object>> getOutLoanCustomerSummaryInfo(Integer signCustomerId) throws Exception;
	
	/**
	 * 根据放款ID，查询当前放款信息及其对应签单信息
	 * @param oId
	 * @return
	 * @throws Exception
	 * 2017年1月6日 下午3:04:58 by SwordLiu
	 */
	public OutLoanCustomer getOutloanCustomerById(Integer oId, String sql) throws Exception;
}
