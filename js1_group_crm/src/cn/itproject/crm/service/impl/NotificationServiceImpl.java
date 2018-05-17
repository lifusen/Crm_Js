package cn.itproject.crm.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.cache.NotificationDB;
import cn.itproject.crm.controller.viewbean.CDEVo;
import cn.itproject.crm.controller.viewbean.FollowSignInfoVo;
import cn.itproject.crm.controller.viewbean.NotificationParam;
import cn.itproject.crm.dao.NotificationDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.service.NotificationService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;
import cn.itproject.crm.websocket.WebSocketUtil;

@Service
public class NotificationServiceImpl extends BaseServiceSupport<Notification> implements NotificationService{
	@Resource
	private NotificationDao notificationDao;
	@Resource
	private EmployeeService employeeService;
	@Override
	protected BaseDao<Notification> getBaseDao() {
		return notificationDao;
	}
	
	@Override
	public Collection<Notification> getAllUnreadNotification(Integer receiverId) throws Exception {
		return NotificationDB.getNotifications(receiverId);
	}
	
	@Override
	public List<Notification> getAllUnreadNotificationOfMySQL() throws Exception {
		return notificationDao.getAllUnreadNotificationOfMySQL();
	}
	
	@Override
	public void addNotification(Notification notification) throws Exception {
		notificationDao.save(notification);
		// 保存到内存中
		NotificationDB.addNotification(notification);
		// WebSocket消息通知
		WebSocketUtil.send(notification);
	}
	
	@Override
	public void addNotification(Integer senderId,Integer receiverCompanyId,Integer receiverDepartmentId,
			Integer receiverId,Integer signCustomerId,Integer customerId,String content) throws Exception{
		CDEVo senderCdeVo = new CDEVo();
		CDEVo receiverCdeVo = new CDEVo();
		senderCdeVo = employeeService.getCDEMapOfEmployeeId().get(senderId);
		// 接收人ID
		if (receiverId != null && receiverId > 0) { // 发送给对应的权证人员
			receiverCdeVo = employeeService.getCDEMapOfEmployeeId().get(receiverId);
		}else if (receiverDepartmentId != null && receiverDepartmentId > 0) { // 发送给权证部门经理
			receiverCdeVo = employeeService.getCDEMapOfDepartmentId().get(receiverDepartmentId);
		}else if (receiverCompanyId != null && receiverCompanyId > 0) {	// 发送给公司负责人
			receiverCdeVo = employeeService.getCDEMapOfCompanyId().get(receiverCompanyId);
		}
		
		String message = null;
		if (senderCdeVo == null) {
			message = LoginUserUtil.getUserName() + content;
		}else{
			// 蜀创一中心业务一部张三将签单客户李四移交给您,请及时处理!
			message = senderCdeVo.getCompanyName()
					+ senderCdeVo.getDepartmentName()
					+ senderCdeVo.getEmployeeName()
					+ content;
		}
		
		// 发送通知
		Notification notification = new Notification();
		notification.setSenderId(senderId);								// 发送人
		notification.setReceiverId(receiverCdeVo.getEmployeeId());		// 接收人
		notification.setContent(message); 								// 消息内容
		notification.setState(0); 										// 默认为未读消息
		notification.setType(Notification.TYPE_SIGN_TRANSFER); 			// 签单移交
		notification.setOperationType(Notification.OPERATION_TYPE_SALESMAN_TO_WARRANT);	// 业务员发给权证
		notification.setSignCustomerId(signCustomerId); 				// 签单客户ID
		notification.setCustomerId(customerId); 						// 客户ID
		notification.setSendTime(new Date());							// 发送时间
		notification.setTypeName("签单移交");
		
		// 发送通知
		addNotification(notification);
	}
	
	public void addNotification(Integer senderId,SignCustomer signCustomer) throws Exception{
		Integer receiverCompanyId = signCustomer.getWarrantCompanyId();				// 权证公司ID
		Integer receiverDepartmentId = signCustomer.getWarrantDepartmentId();		// 权证部ID
		Integer receiverId = signCustomer.getWarrantEmployeeId();					// 权证专员ID
		Integer signCustomerId = signCustomer.getId();								// 签单客户ID
		Integer customerId = signCustomer.getCustomer().getId();					// 客户ID
		String signCustomerName = "";												// 签单客户名称
		String signCustomerContractNO = signCustomer.getContractNO();				// 签单客户合同编号
		try {
			signCustomerName = signCustomer.getSignContacts().get(0).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发送通知
		addNotification(senderId, 
				receiverCompanyId, receiverDepartmentId, receiverId, 
				signCustomerId,customerId, 
				"将"+signCustomerName+",合同编号为:"+signCustomerContractNO+"的签单客户移交给您,请及时处理!");
	}
	
	public void addNotification(OutLoanBelong outLoanBelong,SignCustomer signCustomer) throws Exception{
		Integer senderId = LoginUserUtil.getEmployeeId();
		Integer receiverCompanyId = outLoanBelong.getCompanyId();					// 权证公司ID
		Integer receiverDepartmentId = outLoanBelong.getDepartmentId();				// 权证部ID
		Integer receiverId = outLoanBelong.getWarranterId();						// 权证专员ID
		Integer signCustomerId = signCustomer.getId();								// 签单客户ID
		Integer customerId = signCustomer.getCustomer().getId();					// 客户ID
		String signCustomerName = "";												// 签单客户名称
		String signCustomerContractNO = signCustomer.getContractNO();				// 签单客户合同编号
		try {
			signCustomerName = signCustomer.getSignContacts().get(0).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发送通知
		addNotification(senderId, 
				receiverCompanyId, receiverDepartmentId, receiverId, 
				signCustomerId,customerId, 
				"将"+signCustomerName+",合同编号为:"+signCustomerContractNO+"的签单客户分享给您,请及时处理!");
	}
	
	@Override
	public void addNotification(Integer senderId, FollowSignInfoVo followSignInfoVo) throws Exception {
		Integer receiverCompanyId = followSignInfoVo.getFollowSignWarrantCompanyId();			// 权证公司ID
		Integer receiverDepartmentId = followSignInfoVo.getFollowSignWarrantDepartmentId();		// 权证部ID
		Integer receiverId = followSignInfoVo.getFollowSignWarrantEmployeeId();					// 权证专员ID
		Integer signCustomerId = followSignInfoVo.getFollowSignCustomerId();					// 签单客户ID
		Integer customerId = followSignInfoVo.getFollowCustomerId();							// 客户ID
		String signCustomerName = followSignInfoVo.getFollowSignCustomerName();					// 签单客户名称
		String signCustomerContractNO = followSignInfoVo.getFollowSignCustomerContractNO();		// 签单客户合同编号
		
		if (receiverCompanyId == null && receiverDepartmentId== null && receiverId == null) {
			return;
		}
		// 发送通知
		addNotification(senderId, 
				receiverCompanyId, receiverDepartmentId, receiverId, 
				signCustomerId,customerId, 
				"跟进了合同编号为:"+signCustomerContractNO+"的签单客户"+signCustomerName+",请及时处理!");
	}
	
	
	@Override
	public void updateNotificationState(Integer receiverId,Integer notificationId) throws Exception {
		// 修改状态为已读
		notificationDao.updateNotificationState(notificationId);
		// 从内存中删除
		NotificationDB.removeNotification(receiverId, notificationId);
	}
	@Override
	public List<Notification> getNotificationList(NotificationParam notificationParam) throws Exception {
		return notificationDao.getNotificationList(notificationParam);
	}
	@Override
	public Integer getNotificationListCount(NotificationParam notificationParam) throws Exception {
		return notificationDao.getNotificationListCount(notificationParam);
	}
	
	@Override
	public void addNotification(Employee loginEmployee, Company loginCompany, CustomerFollow customerFollow)
			throws Exception {
		Customer customer = customerFollow.getCustomer();
		Integer customerId = customer.getId();
		String customerName = customer.getName();
		
		
	}

	@Override
	public void addNotificationToSalesman(Integer senderId, FollowSignInfoVo followSignInfoVo) throws Exception {
		Integer receiverCompanyId = followSignInfoVo.getFollowBizCompanyId();					// 业务公司ID
		Integer receiverDepartmentId = followSignInfoVo.getFollowBizDepartmentId();				// 业务部ID
		Integer receiverId = followSignInfoVo.getFollowBizEmployeeId();							// 业务专员ID
		Integer signCustomerId = followSignInfoVo.getFollowSignCustomerId();					// 签单客户ID
		Integer customerId = followSignInfoVo.getFollowCustomerId();							// 客户ID
		String signCustomerName = followSignInfoVo.getFollowSignCustomerName();					// 签单客户名称
		String signCustomerContractNO = followSignInfoVo.getFollowSignCustomerContractNO();		// 签单客户合同编号
		
		if (receiverCompanyId == null && receiverDepartmentId== null && receiverId == null) {
			return;
		}
		// 发送通知
		addNotification(senderId, 
				receiverCompanyId, receiverDepartmentId, receiverId, 
				signCustomerId,customerId, 
				"跟进了合同编号为:"+signCustomerContractNO+"的签单客户"+signCustomerName+",请及时处理!");
	}
}
