package cn.itproject.crm.cache;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月12日
 *
 *       version 1.0
 */
public class CustomStringRedisSerializer implements RedisSerializer<String> {

	private final Charset charset;

	public CustomStringRedisSerializer() {
		this(Charset.forName("GBK"));
	}

	public CustomStringRedisSerializer(Charset charset) {
		Assert.notNull(charset);
		this.charset = charset;
	}

	public String deserialize(byte[] bytes) {
		return (bytes == null ? null : new String(bytes, charset));
	}

	public byte[] serialize(String string) {
		return (string == null ? null : string.getBytes(charset));
	}
}
