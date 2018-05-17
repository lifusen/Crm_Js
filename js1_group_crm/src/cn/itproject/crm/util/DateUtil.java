package cn.itproject.crm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	/**
	 * 获取当前的日期:年月日
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}
	/**
	 * 获取当前的时间,保留到分
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(new Date());
	}
	
	/**
	 * 获取离当前时间的天数
	 * @param oldDate
	 * @return
	 */
	public static Integer getDiffDay(Date oldDate) {
		if (oldDate==null) {
			return null;
		}
		long startTime = oldDate.getTime();
		long endTime = new Date().getTime();
		long diff = endTime-startTime;
		long day = diff/(24*60*60*1000);
		return Integer.parseInt(String.valueOf(day));
	}
	
	/**
	 * 获取到当前时间对应的星期一和星期天的日期
	 * @return
	 */
	public static String[] getCurrentWeekDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String[] strings = new String[2];
		Calendar cal =Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		if(w==0){	// 当期日期是星期天
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
		}
		strings[0] = df.format(cal.getTime());
		strings[1] = df.format(new Date());
		return strings;
	}
}
