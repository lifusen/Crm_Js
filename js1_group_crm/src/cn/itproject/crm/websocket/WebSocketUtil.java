package cn.itproject.crm.websocket;

import javax.websocket.Session;

import com.alibaba.fastjson.JSON;

import cn.itproject.crm.bean.Notification;

public class WebSocketUtil {
	public static void send(Notification notification) {
		Integer key = notification.getReceiverId();
		Session session = WebSocketSessionStore.get(key);
		send(session,notification);
	}
	
	private static void send(Session session,Notification notification) {
		if (session!=null) {
			session.getAsyncRemote().sendText(JSON.toJSONString(notification));
		}
	}
}
