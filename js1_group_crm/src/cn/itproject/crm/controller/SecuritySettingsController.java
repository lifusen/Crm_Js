package cn.itproject.crm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.EmailLog;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.EmailLogService;
import cn.itproject.crm.service.EmailService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.EmailConstant;
import cn.itproject.crm.util.MD5Util;

/**
 * 安全设置
 * 设置找回密码的邮箱
 * 找回密码功能
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/securitySettings")
public class SecuritySettingsController extends BaseController{
	
	@Resource
	private EmailService emailService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private EmailLogService emailLogService; 
	
	@RequestMapping("/setEmailView")
	public String setEmailView(HttpSession session,Model model) throws Exception{
		Employee loginEmployee = (Employee) session.getAttribute(Constant.LOGIN_USER);
		Integer employeeId = loginEmployee.getId();
		String email = employeeService.getEmail(employeeId);
		model.addAttribute("securityEmail", email);
		return "page/securitySettings/setEmail";
	}
	
	@RequestMapping("/sendEmailCode")
	@ResponseBody
	public Map<String, Object> sendEmailCode(String email,Integer type,HttpSession session) throws Exception{
		Integer loginEmployeeId = null;
		String subject = null;
		if (type == 1) {
			Employee loginEmployee = (Employee) session.getAttribute(Constant.LOGIN_USER);
			loginEmployeeId = loginEmployee.getId();
			subject = "安全邮箱设置";
		}else if (type == 2) {
			subject = "找回密码";
		}else{
			subject = "蜀创集团CRM邮箱验证码";
		}
		
		Map<String, Object> result = new HashMap<>();
		String emailVerificationCode = RandomStringUtils.randomNumeric(6);
		session.setAttribute(EmailConstant.EMAIL_VERIFICATION_CODE, emailVerificationCode);
		
		String content =  "【蜀创集团CRM】您的邮箱验证码:<span style='font-weight: bold;'>"+emailVerificationCode+"</span>";
		Integer sendState = 0;
		try {
			emailService.sendHtml(email, subject, content);		// 发送Email
			sendState = 1;		// 发送成功
			result.put("success", true);
			result.put("message", "发送成功!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 记录日志
				EmailLog emailLog = new EmailLog();
				emailLog.setSenderId(loginEmployeeId);
				emailLog.setSenderEmail(EmailConstant.FROM);
				emailLog.setReceiveEmail(email);
				emailLog.setCode(emailVerificationCode);
				emailLog.setSubject(subject);
				emailLog.setContent(content);
				emailLog.setSendTime(new Date());
				emailLog.setState(sendState);
				emailLogService.saveEntity(emailLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result.put("success", false);
		result.put("message", "发送失败,请联系管理员!");
		return result;
	}
	
	/**
	 * 保存Email设置,并判断邮箱验证码是否正确
	 * @param email
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveSecurityEmail")
	@ResponseBody
	public Map<String, Object> saveSecurityEmail(String email,String code,HttpSession session) throws Exception{
		System.out.println("email:"+email);
		System.out.println("code:"+code);
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(email)) {
			result.put("success", false);
			result.put("message", "请输入邮箱!");
			return result;
		}
		if (StringUtils.isBlank(code)) {
			result.put("success", false);
			result.put("message", "请输入邮箱验证码!");
			return result;
		}
		
		String emailVerificationCode = null;
		emailVerificationCode = (String) session.getAttribute(EmailConstant.EMAIL_VERIFICATION_CODE);
		System.out.println("emailVerificationCode:"+emailVerificationCode);
		if (!code.equals(emailVerificationCode)) {
			result.put("success", false);
			result.put("message", "邮箱验证码错误!");
			return result;
		}
		// 保存Email
		Employee loginEmployee = (Employee) session.getAttribute(Constant.LOGIN_USER);
		employeeService.updateEmail(loginEmployee.getId(), email);
		// 从session中删除邮箱验证码
		session.removeAttribute(EmailConstant.EMAIL_VERIFICATION_CODE);
		result.put("success", true);
		result.put("message", "发送成功!");
		return result;
	}
	
	@RequestMapping("/validateEmail")
	@ResponseBody
	public Map<String, Object> validateEmail(String email,HttpSession session) throws Exception{
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(email)) {
			result.put("success",false);
			result.put("message", "请输入邮箱!");
			return result;
		}
		
		// 判断Email是否存在
		boolean isExists = employeeService.emailExists(email);
		if (!isExists) {
			result.put("success",false);
			result.put("message", "您输入的邮箱不存在!");
			return result;
		}
		
		result.put("success",true);
		result.put("message", "邮箱验证成功");
		return result;
	}
	
	
	
	/**
	 * 验证邮箱验证码是否正确
	 * @param email
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/validateEmailCode")
	@ResponseBody
	public Map<String, Object> validateEmailCode(String email,String code,HttpSession session) throws Exception{
		System.out.println("email:"+email);
		System.out.println("code:"+code);
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(email)) {
			result.put("success", false);
			result.put("message", "请输入邮箱!");
			return result;
		}
		if (StringUtils.isBlank(code)) {
			result.put("success", false);
			result.put("message", "请输入邮箱验证码!");
			return result;
		}
		
		String emailVerificationCode = null;
		emailVerificationCode = (String) session.getAttribute(EmailConstant.EMAIL_VERIFICATION_CODE);
		System.out.println("emailVerificationCode:"+emailVerificationCode);
		if (!code.equals(emailVerificationCode)) {
			result.put("success", false);
			result.put("message", "邮箱验证码错误!");
			return result;
		}
		// 从session中删除邮箱验证码
		session.removeAttribute(EmailConstant.EMAIL_VERIFICATION_CODE);
		result.put("success", true);
		result.put("message", "验证成功!");
		return result;
	}
	
	/**
	 * 通过邮箱获取账号
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	@RequestMapping("/getAccountByEmail")
	@ResponseBody
	public String getAccountByEmail(String email) throws Exception{
		String account = employeeService.getAccountByEmail(email);
		return account;
	}
	

	/**
	 * 通过邮箱获取账号
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAccountListByEmail")
	@ResponseBody
	public List<Map<String, Object>> getAccountListByEmail(String email) throws Exception{
		List<Map<String, Object>> list = employeeService.getAccountListByEmail(email);
		return list;
	}
	
	/**
	 * 通过email修改密码
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	@RequestMapping("/updatePasswordByEmail")
	@ResponseBody
	public Map<String, Object> updatePasswordByEmail(String email,String password) throws Exception{
		Map<String, Object> result = new HashMap<>();
		try {
			password = MD5Util.md5(password);
			employeeService.updatePasswordByEmail(email, password);
			result.put("success",true);
			result.put("message", "重置密码成功!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("success",false);
		result.put("message", "重置密码失败!");
		return result;
	}
	
	/**
	 * 通过email修改密码
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatePasswordByEmployeeId")
	@ResponseBody
	public Map<String, Object> updatePasswordByEmployeeId(Integer employeeId,String password) throws Exception{
		Map<String, Object> result = new HashMap<>();
		try {
			password = MD5Util.md5(password);
			employeeService.updatePasswordByEmployeeId(employeeId, password);
			result.put("success",true);
			result.put("message", "重置密码成功!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("success",false);
		result.put("message", "重置密码失败!");
		return result;
	}

}
