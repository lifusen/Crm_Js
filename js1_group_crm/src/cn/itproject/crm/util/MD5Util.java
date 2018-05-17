package cn.itproject.crm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {
	public static String md5(String passwrod) {
		String pw = passwrod;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] by = md.digest(passwrod.getBytes());
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < by.length; i++) {
				int n = by[i] & 0XFF;
				if (n < 16) {
					builder.append("0");
				}
				builder.append(Integer.toHexString(n));
			}
			pw = builder.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pw;
	}
	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}
}
