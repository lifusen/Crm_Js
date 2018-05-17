package cn.itproject.crm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.itproject.crm.bean.Employee;


public class SingleLogin {
	/**
	 * sesssion列表
	 */
	private static Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();
	
    /**
     * 
     *方法描述：单点登录
     * @param session
     * @param userName
     */
 	public synchronized static void singleLogin(HttpSession session, String userName) {
 		if (sessionMap.containsKey(userName)) {
 			HttpSession ses = (HttpSession) sessionMap.get(userName);
 			try {
 				// session的id值不相同才将原session作废
 				if (!(session.getId()).equals(ses.getId())) {
 					ses.invalidate();
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
 		}
 		sessionMap.remove(userName);
 		sessionMap.put(userName, session);
 	}
 	
 	/**
 	 * 
 	 *方法描述：退出
 	 * @param session
 	 * @param userName
 	 */
 	public synchronized static void logout(HttpSession session, String userName) {
 		if (userName!=null) {
 			if (sessionMap.containsKey(userName)) {
 				try {
 					session.invalidate();
 					sessionMap.remove(userName);
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
		}
 	}
 	
 	/**
 	 * 删除用户
 	 * @param users
 	 */
 	public synchronized static void removeUser(List<Employee> users){
 		if (users!= null && users.size()>0) {
			for (Employee user:users) {
				String userName = user.getName();
				if (sessionMap.containsKey(userName)) {
					HttpSession ses = (HttpSession) sessionMap.get(userName);
					try {
						ses.invalidate();
						sessionMap.remove(userName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
 	}
}