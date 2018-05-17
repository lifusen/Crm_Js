package cn.itproject.crm.controller.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class QueryUseTimeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse resp = (HttpServletResponse) response; 2016.12.19 by SwordLiu
		// 开始计时
		long startTime = System.currentTimeMillis();

		// 放行
		filterChain.doFilter(request, response);
		
		
		// 计时结束
		long endTime = System.currentTimeMillis();
		long diffTime = endTime - startTime;
		long diffSecond = diffTime / 1000;
		long minute = diffSecond / 60;
		long second = diffSecond % 60;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("\nQueryUseTimeFilter");
		System.out.println(req.getRequestURI());
		System.out.println(req.getRemoteAddr());
		System.out.println("开始时间:" + dateFormat.format(new Date(startTime)));
		System.out.println("结束时间:" + dateFormat.format(new Date(endTime)));
		System.out.println("diffTime:"+diffTime);
		System.out.println("耗时:" + minute + "分" + second + "秒\n");
		
		QueryUseTime queryUseTime = new QueryUseTime();
		queryUseTime.setDiffTime(diffTime);
		queryUseTime.setDiffTimeString(minute + "分" + second + "秒");
		queryUseTime.setUrl(req.getRequestURI());
		queryUseTime.setStartDate(new Date(startTime));
		queryUseTime.setEndDate(new Date(endTime));
		QueryUseTimeStore.add(queryUseTime);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
