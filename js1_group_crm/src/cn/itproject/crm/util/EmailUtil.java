package cn.itproject.crm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱工具类
 * @author MrLiu
 *
 */
public class EmailUtil {
	
	/**
	 * 验证邮箱格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	
	
	
	public static String readTemplate(String templateName) {
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
