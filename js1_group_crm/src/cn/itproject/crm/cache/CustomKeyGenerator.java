package cn.itproject.crm.cache;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 自定义的key生成器
 * 
 * @author MrLiu
 * @date 2016-3-17
 *
 */
public class CustomKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String className = target.getClass().getName();
		String methodName = method.getName();
		String key = className + "-" + methodName;
		return generateKey(key, params);
	}

	public static Object generateKey(String key, Object... params) {
		// 如果没有参数,则直接返回类名+方法名
		if (params.length > 0) {
			key += "-" + Arrays.deepToString(params);
		}
		System.out.println("key:" + key);
		return key;
	}

}
