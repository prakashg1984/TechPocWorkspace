package com.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.test.model.OrderModel;

@Configuration
public class RedisCacheConfiguration {
	//If using Cacheable annotation - we can avoid this class and use spring data properties directly
	private @Value("${spring.redis.host}")
	String redisHost;
    private @Value("${spring.redis.port}")
    int redisPort;
        
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    RedisTemplate<String,OrderModel> redisTemplate() {
        RedisTemplate<String,OrderModel> redisTemplate = new RedisTemplate<String,OrderModel>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
    
    @Bean
    public CacheManager initRedisCacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory());
        return builder.build();

    }


}
