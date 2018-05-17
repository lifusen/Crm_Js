package cn.itproject.crm.controller.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


public class MySimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	private static Logger log = Logger.getLogger(MySimpleMappingExceptionResolver.class);
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		System.out.println("ERROR======================>");
		log.error("============error------------------------->>>>>>>>>>>>>>>>>");
		log.error(ex.getMessage(), ex);
		log.error("============error------------------------->>>>>>>>>>>>>>>>>");
		return super.doResolveException(request, response, handler, ex);
	}
}
