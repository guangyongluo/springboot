package com.vilin.demo.configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis缓存的注解
 * @author XPRO
 *
 */

@Configuration
public class RedisCacheManagerConfig {

	@Value("${cache.default.expire-time:1800}")
	private int defaultExpireTime;
	@Value("${cache.test.expire-time:1800}")
	private int testExpireTime;
	@Value("${cache.test.name:test}")
	private String testCacheName;
	
	@Bean
	public CacheManager getCacheManager(RedisConnectionFactory factory) {
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		
		defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(defaultExpireTime))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
				.disableCachingNullValues();
		
		Set<String> cacheNames = new HashSet<>();
        cacheNames.add(testCacheName);
		
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put(testCacheName, defaultCacheConfig.entryTtl(Duration.ofSeconds(testExpireTime)));
		
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(defaultCacheConfig)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
		return cacheManager;
	}
}
