package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 签单客户DAO接口
 * 
 * @author MrLiu
 *
 */
public interface SignCustomerDao extends BaseDao<SignCustomer> {
	/**
	 * 查询工作提醒列表
	 * 
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            也大小
	 * @param employeeIds
	 *            员工ID
	 * @param keyword
	 *            搜索关键字
	 * @param orderColumn
	 *            排序列
	 * @param orderByType
	 *            排序方式
	 * @param type
	 *            1:放款客户
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSignCustomerList(Integer pageIndex, Integer pageSize, List<Integer> employeeIds,
			String keyword, String orderColumn, OrderByType orderByType, Integer type) throws Exception;

	/**
	 * 查询工作提醒列表总行数
	 * 
	 * @param employeeIds
	 *            员工ID
	 * @param keyword
	 *            搜索关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getSignCustomerListCount(List<Integer> employeeIds, String keyword, Integer type) throws Exception;

	/**
	 * 获取某个客户的所有签单ID
	 * 
	 * @param cId
	 * @return
	 */
	public List<Integer> getSignCustomerIdsByCId(Integer cId) throws Exception;

	/**
	 * 签单客户维护列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	public List<Map<String, Object>> getMaintainList(Integer pageIndex, Integer pageSize, String keyword)
			throws Exception;

	/**
	 * 签单客户维护数量
	 * 
	 * @param keyword
	 * @return
	 */
	public Integer getMaintainCount(String keyword) throws Exception;

	public void updateWarrant(Integer[] idArray, Integer companyId, Integer dId, Integer eId) throws Exception;

	public List<Map<String, Object>> getSignCustomerSummaryInfo(Integer customerId) throws Exception;

	public SignCustomer getSignCustomer(Integer customerId, Integer signCustomerId) throws Exception;

	/**
	 * 更新签单客户orderType，用于在有效客户列表直接显示客户放款进度状态
	 * 
	 * @param signCustomerId
	 * @param type
	 * @throws Exception
	 *             2017年1月17日 下午2:24:29 by SwordLiu
	 */
	public void updateOrderType(Integer signCustomerId, Integer type) throws Exception;

	/**
	 * 查询合同编号是否已经存在
	 */
	public SignCustomer getSignCustomerByContactNo(String contractNO) throws Exception;

}
