package cn.itproject.crm.util;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 处理倒计时
 * @author jianghan
 *
 */
public class HandleFllowDateFunction {
	
	public static String fllowDate(Date fllowDate) {
		// 实时获取公共池天数
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Integer commonPoolDay = (Integer) request.getServletContext().getAttribute(Constant.COMMON_POOL_DAY);
		
		if (fllowDate==null) {
			return "";
		}System.out.println("commonPoolDay:::::::::::::::::::::::::::::::::::::"+commonPoolDay);
	   Calendar cal = Calendar.getInstance();  
       cal.setTime(fllowDate);  
       long fllowTime = cal.getTimeInMillis();
       cal.setTime(new Date());  
       long nowTime = cal.getTimeInMillis();       
       long between_days=(nowTime-fllowTime)/(1000*3600*24);
       int day = (int) (commonPoolDay-between_days);
       return String.valueOf(day);
	}
}
