package cn.itproject.crm.dao;

import java.util.Date;
import java.util.List;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.db.BaseDao;

/**
 * 客户DAO接口
 * @author MrLiu
 *
 */
public interface CustomerDao extends BaseDao<Customer>{

	List<Customer> getListByState(Integer pageIndex, Integer pageSize,
			Integer state,Integer eId) throws Exception;

	void updateState(Integer cId, Integer state) throws Exception;
	
	void updateVisibility(Integer cId, Integer visibility) throws Exception;

	List<Customer> getAllotToEmployeeList(Integer roleId, Integer dId,Integer pageIndex, Integer pageSize) throws Exception;

	List<Customer> getNewList(Integer pageIndex, Integer pageSize, Integer[] id,String keyStr) throws Exception;

	Integer getCountByState(Integer state,Integer eId)throws Exception;

	Integer getAllotToEmployeeCount(Integer roleId, Integer dId)throws Exception;

	Integer getNewOrderCount(Integer[] id,String keyStr)throws Exception;

	List<Customer> getAssignedOrderList(Integer pageIndex, Integer pageSize,
			Integer[] id,String keyStr)throws Exception;

	Integer getAssignedOrderCount(Integer[] id,String keyStr)throws Exception;

	List<Customer> getReceiveOrderList(Integer pageIndex, Integer pageSize,
			Integer[] id,String keyStr)throws Exception;

	Integer getReceiveOrderCount(Integer[] id,String keyStr)throws Exception;

	List<Customer> getValidCustomer(Integer pageIndex, Integer pageSize,
			Integer[] id,String keyStr)throws Exception;

	Integer getValidCustomer(Integer[] id,String keyStr)throws Exception;
	
	/**
	 * 累加客户通话次数
	 */
	void addCustomerCallCount(Integer customerId) throws Exception;
	
	/**
	 * 累加客户上门次数
	 */
	void addCustomerVisitCount(Integer customerId) throws Exception;

	Integer getCountByAddType(int ordinal,int id) throws Exception;

	List<Customer> getListByAddType(Integer rId,Integer count) throws Exception;

	List<Customer> getApplyNullity(Integer pageIndex, Integer pageSize,
			Integer[] id, String keyStr) throws Exception;

	Integer getApplyNullityCount(Integer[] ids, String keyStr) throws Exception;

	/**
	 * 根据客户ID获取其是否跟进记录
	 * @param customerId 客户ID
	 * @return null:未跟进/0:已跟进
	 * @throws Exception
	 */
	@Deprecated
	Integer getIsFollow(Integer customerId) throws Exception;

	/**
	 * 根据客户ID修改是否跟进记录
	 * @param customerId 客户ID
	 * @throws Exception
	 */
	@Deprecated
	void updateFollowState(Integer customerId) throws Exception;
	
	/**
	 * 根据客户ID获取客户的签单状态
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	String getSignState(Integer customerId) throws Exception;
	
	/**
	 * 根据ID修改客户的签单状态
	 * @param customerId
	 * @throws Exception
	 */
	void updateSignState(Integer customerId,String signState) throws Exception;

	List<Customer> getByState(Integer pageIndex, Integer pageSize,
			Integer[] id, String keyStr, Integer[] states) throws Exception;

	Integer getCountByState(Integer[] ids, String keyStr, Integer[] states) throws Exception;
	
	/**
	 * 添加导入的客户
	 * @param customer
	 * @throws Exception
	 */
	void addImportCustomer(Customer customer) throws Exception;

	void batchUpdate(Integer companyId,Integer dId,List<Integer> ids, int state) throws Exception;

	/**
	 * 获取客户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Customer getBasicCustomer(Integer id) throws Exception;

	List<Integer> getListByFllowDate(Integer day) throws Exception;

	/**
	 * 获取客户状态
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	Integer getCustomerState(Integer customerId) throws Exception;

	Integer getCountByEId(Integer id) throws Exception;
	
	/**
	 * 修改客户的最后一次跟进时间 
	 * @param customerId
	 */
	void updateFlowDate(Integer customerId) throws Exception;

	/**
	 * 修改客户的最后一次修改时间
	 * @param customerId
	 * @throws Exception
	 */
	void updateTime(Integer customerId) throws Exception;

	Integer getCountByPhoneAndState(String phone, List<Integer> validlist) throws Exception;
	/**
	 * 修改客户的等级
	 * @param id 客户ID
	 * @param level 客户等级名称
	 * @throws Exception
	 */
	public void updateCustomerLevel(Integer id,String level) throws Exception;
	
	/**
	 * 修改客户的关注等级
	 * @param id 客户ID
	 * @param level 客户的关注等级名称
	 * @throws Exception
	 */
	public void updateAttentionLevel(Integer id,String level) throws Exception;

	Integer getReleaseCountById(Integer id, Date date)throws Exception;

	Integer getReceiveCountById(Integer id, Date date) throws Exception;

	/**
	 * 当跟进类型和提醒时间不为null时,则更新该客户最后一次提醒的信息
	 * @param customerFollow
	 * @throws Exception
	 */
	void updateLastRemindInfo(CustomerFollow customerFollow) throws Exception;

	/**
	 * 
	 * @param customerId
	 * @param remindTime
	 * @throws Exception
	 */
	void updateLastRemindTime(Integer customerId, Date remindTime) throws Exception;

	void updateWarrantLastRemindInfo(CustomerFollow customerFollow) throws Exception;
	
	/**
	 * 跟新客户最后一次做的客户进度情况记录
	 * @param customerId 客户ID
	 * @param type 进度状态码
	 * @throws Exception
	 * 2017年1月10日 上午10:31:49 by SwordLiu
	 */
	void updateListCourse(Integer customerId, Integer type) throws Exception;

	Integer getValidCustomer(Integer eId) throws Exception;
}
