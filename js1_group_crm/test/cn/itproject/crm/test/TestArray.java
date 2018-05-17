package cn.itproject.crm.test;

import java.util.Arrays;

public class TestArray {
	public static void main(String[] args) {
		Object[] objects = new Object[2];
		objects[0] = new Object[]{1,2,3};
		objects[1] = new Object[]{"A","B","C"};
		System.out.println(objects);
		System.out.println(Arrays.toString(objects));
		System.out.println(Arrays.deepToString(objects));
	}
}
