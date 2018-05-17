package cn.itproject.crm.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

public class WebSocketSessionStore {
	private static Map<Integer, Session> clients = new ConcurrentHashMap<>();
	
	public static void put(Integer key,Session session) {
		clients.put(key, session);
	}
	
	public static Session get(Integer key) {
		return clients.get(key);
	}
	
	public static void remove(Integer key) {
		clients.remove(key);
	}
	
	public static void containsKey(Integer key) {
		clients.containsKey(key);
	}
	
	public static Map<Integer, Session> getMap() {
		return clients;
	}
	
}
