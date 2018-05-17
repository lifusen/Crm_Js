package cn.itproject.crm.controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 记录控制器参数
 * @author MrLiu
 *
 */
public class ControllerParameterFilter implements Filter{
	private static Logger log = Logger.getLogger(ControllerParameterFilter.class);
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		log.info("---------------------------------- request start ----------------------------------");
		log.info("url:"+request.getServletPath());
		Map<String, String[]> paramsMap = request.getParameterMap();
		if (paramsMap!=null&&paramsMap.size()>0) {
			log.info("params:"+paramsMap.size());
			for (Entry<String, String[]> kv : paramsMap.entrySet()) {
				String key = kv.getKey();
				String[] values = kv.getValue();
				if (values==null) {										//没有值
					log.info(key);
				}else if (values.length>1) {							//多个值
					log.info(key+":"+Arrays.toString(values));
				}else {													//一个值
					log.info(key+":"+values[0]);	
				}
			}
		}
		log.info("---------------------------------- request end ----------------------------------");
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
