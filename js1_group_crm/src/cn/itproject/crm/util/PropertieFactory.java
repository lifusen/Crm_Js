package cn.itproject.crm.util;

import java.util.PropertyResourceBundle;


public class PropertieFactory {
	static PropertyResourceBundle bundle;
	static{
		bundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle("crm");
	}
	/**
	 * 获取配置文件值
	 * @param key
	 * @return
	 */
	public static String getProVal(String key){
		String value = "";
		try {
			if (key != null) {
				Object objValue = bundle.handleGetObject(key);
				if (objValue != null) {
					value = objValue.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
