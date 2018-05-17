package cn.itproject.crm.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.itproject.crm.bean.Notification;

public class NotificationDB {

	private static Map<Integer, Map<Integer, Notification>> map = new HashMap<>();

	static {
		
	}

	public static void addNotification(Notification notification) {
		if (notification == null) {
			return;
		}
		Integer receiverId = notification.getReceiverId();
		Integer notificationId = notification.getId();
		if (receiverId == null || notificationId == null) {
			return;
		}
		
		if (map.containsKey(receiverId)) {
			map.get(receiverId).put(notificationId, notification);
		} else {
			Map<Integer, Notification> notificationMap = new LinkedHashMap<>();
			notificationMap.put(notificationId, notification);
			map.put(receiverId, notificationMap);
		}
	}

	public static Collection<Notification> getNotifications(Integer receiverId) {
		Map<Integer, Notification> nMap = map.get(receiverId);
		if (nMap != null) {
			return nMap.values();
		}
		return null;
	}
	
	// 修改状态为已读之后,从map中删除数据
	public static void removeNotification(Integer receiverId, Integer notificationId) {
		Map<Integer, Notification> nMap = map.get(receiverId);
		if (nMap != null) {
			nMap.remove(notificationId);
		}
	}
	
	public static void main(String[] args) {
		// 获取receiverId:10的通知集合
		Map<Integer, Notification> map10 = map.get(10);
		for (Integer id : map10.keySet()) {
			System.out.println("key:" + id);
			System.out.println("value:" + map10.get(id));
		}
		System.out.println("collection:");
		Collection<Notification> collection = map10.values();
		for (Notification notification2 : collection) {
			System.out.println(notification2);
		}
		System.out.println(JSON.toJSONString(collection, true));

		System.out.println("删除10-1");
		printMap();

	}

	

	public static void printMap() {
		System.out.println("size:" + map.size());
		for (Integer key : map.keySet()) {
			System.out.println("receiverId:" + key);
			Map<Integer, Notification> nMap = map.get(key);
			for (Integer key2 : nMap.keySet()) {
				System.out.println("\tnotificationId:" + key2 + "  " + nMap.get(key2));
			}
		}
	}
}
