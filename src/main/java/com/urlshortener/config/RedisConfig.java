package com.urlshortener.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
	
	Logger log = LogManager.getLogger(RedisConfig.class);
	
	
	@Autowired
	private Environment env;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		String hostName = env.getProperty("spring.redis.host");
		Integer port = Integer.valueOf(env.getProperty("spring.redis.port"));
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(hostName,port);
		return new JedisConnectionFactory(configuration);
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
