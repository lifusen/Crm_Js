package cn.itproject.crm.service;

import java.util.Collection;
import java.util.List;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.controller.viewbean.FollowSignInfoVo;
import cn.itproject.crm.controller.viewbean.NotificationParam;

public interface NotificationService extends BaseService<Notification>{
	
	/**
	 * 根据接收人ID获取所有的未读消息列表
	 * @param receiverId
	 * @return
	 * @throws Exception
	 */
	public Collection<Notification> getAllUnreadNotification(Integer receiverId) throws Exception;
	
	
	/**
	 * 从数据库中获取所有未读消息列表
	 * @param receiverId
	 * @return
	 * @throws Exception
	 */
	public List<Notification> getAllUnreadNotificationOfMySQL() throws Exception;
	
	/**
	 * 添加通知
	 * @param notification
	 * @throws Exception
	 */
	public void addNotification(Notification notification) throws Exception;
	
	public void addNotification(Integer senderId,Integer receiverCompanyId,Integer receiverDepartmentId,
			Integer receiverId,Integer signCustomerId,Integer customerId,String content) throws Exception;
	
	public void addNotification(Integer senderId,SignCustomer signCustomer) throws Exception;
	
	/**
	 * 修改通知的状态为已读
	 * @param id
	 * @throws Exception
	 */
	public void updateNotificationState(Integer receiverId,Integer notificationId) throws Exception;
	
	/**
	 * 获取通知列表数据
	 * @param notificationParam
	 * @return
	 * @throws Exception
	 */
	public List<Notification> getNotificationList(NotificationParam notificationParam) throws Exception;

	/**
	 * 获取通知列表数量
	 * @param notificationParam
	 * @return
	 * @throws Exception
	 */
	public Integer getNotificationListCount(NotificationParam notificationParam) throws Exception;

	public void addNotification(Employee loginEmployee, Company loginCompany, CustomerFollow customerFollow) throws Exception;

	public void addNotification(Integer senderId, FollowSignInfoVo followSignInfoVo) throws Exception;

	public void addNotificationToSalesman(Integer senderId, FollowSignInfoVo followSignInfoVo) throws Exception;

	/**
	 * 分享客户时发送消息
	 * @param outLoanBelong
	 * @param signCustomer
	 * @throws Exception
	 * 2017年1月16日 下午5:32:56 by SwordLiu
	 */
	public void addNotification(OutLoanBelong outLoanBelong, SignCustomer signCustomer) throws Exception;
	
}
