package cn.itproject.crm.util;

public class StringUtil {
	public static void main(String[] args) {
		String string = substring("12345678", 8);
		System.out.println(string);
	}
	/**
	 * 截取指定长度的字符串
	 * @param srcString 原字符串
	 * @param retainLength 指定长度
	 * @return 新字符串
	 */
	public static String substring(String srcString,int retainLength) {
		if (srcString==null) {
			return null;
		}
		//去空格
		srcString = srcString.trim();
		String newString = null;
		int srcLength = srcString.length();
		if (srcLength > retainLength) {
			newString = srcString.substring(0,retainLength);
		}else if (srcLength<retainLength) {
			newString = srcString;
		}else{
			newString = srcString;
		}
		return newString;
	}
}
