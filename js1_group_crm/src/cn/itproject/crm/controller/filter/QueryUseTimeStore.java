package cn.itproject.crm.controller.filter;

import java.util.ArrayList;
import java.util.List;

public class QueryUseTimeStore {
	private static List<QueryUseTime> list = new ArrayList<>();
	private static final Integer MILLISCOND = 1000;
	public static void add(QueryUseTime queryUseTime) {
		if (list.size() > 50) {
			list = new ArrayList<>();
		}
		Long diffTime = queryUseTime.getDiffTime();
		if (diffTime > MILLISCOND) {
			list.add(queryUseTime);
		}
		
	}
	public static QueryUseTime[] getListWithDiffTimeDESC() {
		//冒泡排序,倒序排列
		QueryUseTime[] queryUseTimes = new QueryUseTime[list.size()]; 
		list.toArray(queryUseTimes);
		for (int i = 0; i < queryUseTimes.length-1; i++) {
			for (int j = 0; j < queryUseTimes.length-1-i; j++) {
				QueryUseTime temp = queryUseTimes[j];
				if (temp.getDiffTime() < queryUseTimes[j+1].getDiffTime()) {
					queryUseTimes[j] = queryUseTimes[j+1];
					queryUseTimes[j+1] = temp;
				}
			}
		}
		return queryUseTimes;
	}
	
	public static QueryUseTime[] getListWithDiffTimeASC() {
		//冒泡排序,倒序排列
		QueryUseTime[] queryUseTimes = new QueryUseTime[list.size()]; 
		list.toArray(queryUseTimes);
		for (int i = 0; i < queryUseTimes.length-1; i++) {
			for (int j = 0; j < queryUseTimes.length-1-i; j++) {
				QueryUseTime temp = queryUseTimes[j];
				if (temp.getDiffTime() > queryUseTimes[j+1].getDiffTime()) {
					queryUseTimes[j] = queryUseTimes[j+1];
					queryUseTimes[j+1] = temp;
				}
			}
		}
		return queryUseTimes;
	}

	public static QueryUseTime[] getListWithStartDateDESC() {
		//冒泡排序,倒序排列
		QueryUseTime[] queryUseTimes = new QueryUseTime[list.size()]; 
		list.toArray(queryUseTimes);
		for (int i = 0; i < queryUseTimes.length-1; i++) {
			for (int j = 0; j < queryUseTimes.length-1-i; j++) {
				QueryUseTime temp = queryUseTimes[j];
				if (temp.getStartDate().getTime() < queryUseTimes[j+1].getStartDate().getTime()) {
					queryUseTimes[j] = queryUseTimes[j+1];
					queryUseTimes[j+1] = temp;
				}
			}
		}
		return queryUseTimes;
	}
	
	public static QueryUseTime[] getListWithStartDateASC() {
		//冒泡排序,倒序排列
		QueryUseTime[] queryUseTimes = new QueryUseTime[list.size()]; 
		list.toArray(queryUseTimes);
		for (int i = 0; i < queryUseTimes.length-1; i++) {
			for (int j = 0; j < queryUseTimes.length-1-i; j++) {
				QueryUseTime temp = queryUseTimes[j];
				if (temp.getStartDate().getTime() > queryUseTimes[j+1].getStartDate().getTime()) {
					queryUseTimes[j] = queryUseTimes[j+1];
					queryUseTimes[j+1] = temp;
				}
			}
		}
		return queryUseTimes;
	}
}
