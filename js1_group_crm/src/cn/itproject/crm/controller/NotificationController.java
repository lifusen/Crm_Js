package cn.itproject.crm.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.NotificationParam;
import cn.itproject.crm.service.NotificationService;

@Controller
@Scope("prototype")
@RequestMapping("/notification")
public class NotificationController extends BaseController{
	private static Integer testI = 0;
	
	@Resource
	private NotificationService notificationService;
	
	@RequestMapping("/testAdd")
	@ResponseBody
	public String testAdd(Integer receiverId) throws Exception {
		Notification notification = new Notification();
		notification.setSenderId(105);
		notification.setContent((testI++)+"新增客户：蜀创1中心业务一部张三的客户李哥完成签单并移交给您! 移交时间:2016年6月5号15点10分,请及时处理!");
		notification.setType(1);
		notification.setSendTime(new Date());
		notification.setReceiverId(receiverId);
		
		notificationService.addNotification(notification);
		return "ok";
	}
	
	@RequestMapping("/getAllUnreadNotification")
	@ResponseBody
	public Collection<Notification> getAllUnreadNotification() throws Exception {
		// 获取当前登录对象ID
		Integer loginEmployeeId = getLoginEmployee().getId();
		Collection<Notification> notifications = notificationService.getAllUnreadNotification(loginEmployeeId);
		if (notifications == null) {
			return new ArrayList<>();
		}
		return notifications;
	}
	
	@RequestMapping("/getNotificationListView")
	public String getNotificationListView() {
		System.out.println("getNotificationListView....");
		return "page/notification/notificationListView";
	}
	
	@RequestMapping("/getNotificationList")
	public String getNotificationList(NotificationParam notificationParam,Model model) throws Exception{
		setEmployeeCacheMap(model);			// 设置员工
		// 获取通知列表
		notificationParam.setReceiverId(getLoginEmployee().getId());
		List<Notification> notifications = notificationService.getNotificationList(notificationParam);
		model.addAttribute("notifications", notifications);
		Integer count = notificationService.getNotificationListCount(notificationParam);
		builderParam(model, notificationParam.getPageIndex(), notificationParam.getPageSize(), count,
				"notification/getNotificationList.do?keyword="+notificationParam.getKeyword(), "notificationTableWrapper");
		return "page/notification/notificationTable";
	}
	
	/**
	 * 修改通知的状态为已读
	 * @param receiverId
	 * @param notificationId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateNotificationState")
	@ResponseBody
	public boolean updateNotificationState(Integer receiverId,Integer notificationId) throws Exception{
		notificationService.updateNotificationState(receiverId, notificationId);
		return true;
	}
	
	
}
