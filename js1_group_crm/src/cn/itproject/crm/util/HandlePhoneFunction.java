package cn.itproject.crm.util;

/**
 * 隐藏手机号码中间4位的自定义函数
 * @author MrLiu
 *
 */
public class HandlePhoneFunction {
	
	public static String phone(String phone) {
		if (phone==null) {
			return null;
		}
		phone = phone.trim();
		if (phone.equals("")) {
			return "";
		}
		String handlePhoneString = null;
		if (phone.length()>=7) {
			String beforeString = phone.substring(0,3);
			String middleString = phone.substring(3,7);
			String afterString = phone.substring(7);
			handlePhoneString = beforeString+"****"+afterString;
		}else {
			handlePhoneString = phone;
		}
		
		return handlePhoneString;
	}
	public static void main(String[] args) {
		phone("15828636400");
	}
}
