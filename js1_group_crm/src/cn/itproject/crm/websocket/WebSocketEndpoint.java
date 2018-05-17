package cn.itproject.crm.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketEndpoint {
	private static final String CLIENT_KEY = "userId";
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("onOpen....");
		printSessionInfo(session);
		WebSocketSessionStore.put(getClientKey(session), session);
	}
	
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose.....");
		printSessionInfo(session);
		WebSocketSessionStore.remove(getClientKey(session));
	}
	
	@OnMessage
	public void onMessage(String message,Session session) {
		System.out.println("onMessage....");
		System.out.println(message);
		System.out.println(session);
		printSessionInfo(session);
	}
	
	@OnError
	public void onError(Session session,Throwable error) {
		System.out.println("onError....");
		System.out.println(session);
		printSessionInfo(session);
		error.printStackTrace();
	}
	
	private void printSessionInfo(Session session) {
		System.out.println(session.getId());
		System.out.println(session.getRequestURI());
		System.out.println(session.getQueryString());
		System.out.println(session.getRequestParameterMap());
		System.out.println(session.getPathParameters());
	}
	
	private Integer getClientKey(Session session) {
		String key = session.getRequestParameterMap().get(CLIENT_KEY).get(0);
		if (key != null) {
			return Integer.parseInt(key);
		}
		return null;
	}
	
}
