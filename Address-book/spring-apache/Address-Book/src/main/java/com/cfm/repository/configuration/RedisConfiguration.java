package com.cfm.repository.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

	@Value("${redis.hostname}")
	private String redisHostName;

	@Value("${redis.port}")
	private int redisPort;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisHostName, redisPort));
	}

	@Bean
	RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
