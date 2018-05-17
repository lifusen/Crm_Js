package cn.itproject.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		System.out.println(date.format(now));

	}

}
