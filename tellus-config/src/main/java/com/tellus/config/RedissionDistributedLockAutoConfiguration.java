package com.tellus.config;

import com.tellus.config.redis.IDistributedLock;
import com.tellus.config.redis.RedissionDistributedLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redission 分布式锁
 *
 * @author Roy
 * @date 2020/5/22 13:55
 */
@Configuration
@ConditionalOnClass(name = "org.redission.api.RedissonClient")
public class RedissionDistributedLockAutoConfiguration {

    @Bean
    public IDistributedLock distributedLock(RedissonClient redissonClient) {
        return new RedissionDistributedLock(redissonClient);
    }
}
