package cn.itproject.crm.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.controller.viewbean.NotificationParam;
import cn.itproject.crm.dao.NotificationDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;
import cn.itproject.crm.util.StringUtil;

@Repository
public class NotificationDaoImpl extends BaseDaoSupport<Notification> implements NotificationDao{

	@Override
	public void updateNotificationState(Integer notificationId) throws Exception {
		String sql = "update notification set state = 1 where id = :id";
		getSession().createSQLQuery(sql)
			.setInteger("id", notificationId)
			.executeUpdate();;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotificationList(NotificationParam notificationParam) throws Exception {
		String sql = "select n.* from notification n inner join employee e on n.receiverId = e.id where 1 = 1";
		Integer receiverId = notificationParam.getReceiverId();
		Integer type = notificationParam.getType();
		Integer state = notificationParam.getState();
		String keyword = notificationParam.getKeyword();
		
		/**
		 
		// 设置当前登录用户ID(接收人ID),如果是超级管理员,查看所有的消息通知
		Integer loginRoleId = LoginUserUtil.getLoginRoleId();
		if (Constant.superAdmin == loginRoleId) {			// 超级管理员
			
		}else if (Constant.managerRoleIds.contains(loginRoleId)) {		// 业务中心管理层
			sql += " and e.companyId = " + LoginUserUtil.getCompanyId();
		}else if (Constant.businessManagerRoleId == loginRoleId) {		// 业务部经理
			sql += " and e.departmentId = " + LoginUserUtil.getLoginDepartmentId();
		}else if (Constant.salesmanRoleIds.contains(loginRoleId)) {		// 业务员
			sql += " and n.receiverId = " + receiverId;
		}else if (loginRoleId == 2000) {				// 权证超级管理员
			sql += " and n.operationType in (1,3)";
		}else if (loginRoleId == 2001) {				// 权证中心管理员
			sql += " and e.companyId = " + LoginUserUtil.getCompanyId();
		}else if (loginRoleId == 2002) {		// 权证部经理
			sql += " and e.departmentId = " + LoginUserUtil.getLoginDepartmentId();
		}else if (loginRoleId == 2003) {		// 权证专员
			sql += " and n.receiverId = " + receiverId;
		}
		* 
		 */
		
		if(receiverId != null){
			sql += " and n.receiverId = " + receiverId;
		}
		if (type != null) {
			sql += " and n.type = " + type;
		}
		if (state != null) {
			sql += " and n.state = " + state;
		}
		if (StringUtils.isNotBlank(keyword)) {
			sql += " and (n.content like '%"+keyword+"%' or n.typeName like '%"+keyword+"%')";
		}
		sql += " order by n.id desc";
		System.out.println(sql);
		Query query = getSession().createSQLQuery(sql);
		Integer pageIndex = notificationParam.getPageIndex();
		Integer pageSize = notificationParam.getPageSize();
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.aliasToBean(Notification.class));  
		return query.list();
	}

	@Override
	public Integer getNotificationListCount(NotificationParam notificationParam) throws Exception {
		String sql = "select count(n.id) from notification n inner join employee e on n.receiverId = e.id where 1 = 1";
		Integer receiverId = notificationParam.getReceiverId();
		Integer type = notificationParam.getType();
		Integer state = notificationParam.getState();
		String keyword = notificationParam.getKeyword();
		
		/**
		 
		// 设置当前登录用户ID(接收人ID),如果是超级管理员,查看所有的消息通知
		Integer loginRoleId = LoginUserUtil.getLoginRoleId();
		if (Constant.superAdmin == loginRoleId) {			// 超级管理员
			
		}else if (Constant.managerRoleIds.contains(loginRoleId)) {		// 业务中心管理层
			sql += " and e.companyId = " + LoginUserUtil.getCompanyId();
		}else if (Constant.businessManagerRoleId == loginRoleId) {		// 业务部经理
			sql += " and e.departmentId = " + LoginUserUtil.getLoginDepartmentId();
		}else if (Constant.salesmanRoleIds.contains(loginRoleId)) {		// 业务员
			sql += " and n.receiverId = " + receiverId;
		}else if (loginRoleId == 2000) {				// 权证超级管理员
			sql += " and n.operationType in (1,3)";
		}else if (loginRoleId == 2001) {				// 权证中心管理员
			sql += " and e.companyId = " + LoginUserUtil.getCompanyId();
		}else if (loginRoleId == 2002) {		// 权证部经理
			sql += " and e.departmentId = " + LoginUserUtil.getLoginDepartmentId();
		}else if (loginRoleId == 2003) {		// 权证专员
			sql += " and n.receiverId = " + receiverId;
		}
		* 
		 */
		
		if(receiverId != null){
			sql += " and n.receiverId = " + receiverId;
		}
		if (type != null) {
			sql += " and n.type = " + type;
		}
		if (state != null) {
			sql += " and n.state = " + state;
		}
		if (StringUtils.isNotBlank(keyword)) {
			sql += " and (n.content like '%"+keyword+"%' or n.typeName like '%"+keyword+"%')";
		}
		System.out.println(sql);
		Query query = getSession().createSQLQuery(sql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getAllUnreadNotificationOfMySQL() throws Exception {
		String hql = "from Notification where state = 0";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
