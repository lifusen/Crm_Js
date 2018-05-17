package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.controller.viewbean.NotificationParam;
import cn.itproject.crm.db.BaseDao;

public interface NotificationDao extends BaseDao<Notification>{

	void updateNotificationState(Integer notificationId) throws Exception;

	List<Notification> getNotificationList(NotificationParam notificationParam) throws Exception;
	
	Integer getNotificationListCount(NotificationParam notificationParam) throws Exception;

	List<Notification> getAllUnreadNotificationOfMySQL() throws Exception;


}
