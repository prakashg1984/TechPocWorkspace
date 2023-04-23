package com.pg.redis.config;


import org.springframework.context.annotation.Configuration;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.ExpirableLockRegistry;

@Configuration
public class RedisDistributedLockConfiguration {

    private static final String LOCK_REGISTRY_REDIS_KEY = "MY_REDIS_KEY";
    private static final Duration RELEASE_TIME_DURATION = Duration.ofSeconds(30);

    @Bean("LOCK_REGISTRY_BEAN")
    public RedisLockRegistry lockRegistry(RedisConnectionFactory redisConnectionFactory) {
        RedisLockRegistry redisLockRegistry =
                new RedisLockRegistry(redisConnectionFactory, LOCK_REGISTRY_REDIS_KEY,
                        RELEASE_TIME_DURATION.toMillis());
        return redisLockRegistry;
    }
}
