package cn.itproject.crm.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.SystemContext;
import cn.itproject.crm.controller.viewbean.SystemInfo;
import cn.itproject.crm.util.Constant;

/**
 * 系统安全过滤器
 * @author Administrator
 *
 */
public class SysSecureFilter implements Filter{
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=" + ENCODING;

    //定义登录页面和错误页面
    private static String loginPage;
    private static String errorPage;
    @Override
    public void destroy(){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletResponse rep = (HttpServletResponse) response;
        HttpServletRequestWrapper req = new HttpServletRequestWrapper((HttpServletRequest) request);
        req.setCharacterEncoding(ENCODING);
        getCompanyId((HttpServletRequest) request);
        //当前请求的路径
        String url = String.valueOf(req.getRequestURL());
        // 指定响应编码
        rep.setCharacterEncoding(ENCODING);
        rep.setContentType(CONTENT_TYPE);
        Employee employee =  (Employee) req.getSession().getAttribute(Constant.LOGIN_USER);
        String contextPath = req.getContextPath();
        // 已登录 && session有效,继续执行请求的路径
        if (url.endsWith(errorPage) || url.endsWith(loginPage) || url.endsWith("/crm") || url.endsWith("/crm/") || 
        		url.endsWith("login.do") || 
        		url.endsWith("logout.do") || 
        		url.endsWith("extra_lock.jsp") || 
        		url.endsWith("lock.do") ||
        		url.endsWith("unLock.do") ||
        		url.endsWith("init.do")||
        		url.contains("/test/")||
        		url.endsWith("forgetPassword.jsp")||
        		url.endsWith("validateEmail.do")||
        		url.endsWith("sendEmailCode.do")||
        		url.endsWith("validateEmailCode.do")||
        		url.endsWith("updatePasswordByEmail.do")||
        		url.endsWith("getAccountByEmail.do")||
        		url.endsWith("getAccountListByEmail.do")||
        		url.endsWith("updatePasswordByEmployeeId.do")||
        		(employee!=null && 
        		(employee.getLoginLock()==null || 
        		!employee.getLoginLock()))
               ) { 
            chain.doFilter(req, rep);
        } else {// 未登录 || session无效,进入提示页
        	if (employee!=null && 
        			employee.getLoginLock()!=null && 
        			employee.getLoginLock()) {
				// 锁定
        		rep.getWriter().println("<script type='text/javascript'>window.location='"+contextPath+"/extra_lock.jsp';</script>");
			}else {
				req.getSession().invalidate();
				rep.getWriter().println("<script type='text/javascript'>window.location='"+contextPath+"/login.jsp';</script>");
			}
        } // end 未登录
    }
    
    
    private void getCompanyId(HttpServletRequest request) {
    	String companyIds = request.getParameter("companyIds");
		SystemInfo systemInfo = new SystemInfo();
		systemInfo.setCompanyIds(companyIds);
		SystemContext.setSysContext(systemInfo);
	}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	loginPage = filterConfig.getInitParameter("loginPage");
    	errorPage = filterConfig.getInitParameter("errorPage");
    }
}