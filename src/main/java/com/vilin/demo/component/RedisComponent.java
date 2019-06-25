package com.vilin.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public void set(String key, String value) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		if(!redisTemplate.hasKey(key)) {
			ops.set(key, value);
		}else {
			System.out.println("this key = " + ops.get(key));
		}
	}
	
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
}
