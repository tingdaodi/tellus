package com.tellus.config.redis;

import lombok.SneakyThrows;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Redission 同步锁实现
 *
 * @author Roy
 * @date 2020/5/21 14:49
 */
public class RedissionDistributedLock implements IDistributedLock {

    private final RedissonClient redissonClient;

    public RedissionDistributedLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    @SneakyThrows
    public boolean lock(String key, long expire, int retryTimes, long sleepMills) {
        RLock lock = this.redissonClient.getLock(key);
        long waitTime = retryTimes * sleepMills;
        return lock.tryLock(waitTime, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean releaseLock(String key) {
        RLock lock = this.redissonClient.getLock(key);
        return !lock.isLocked() || lock.forceUnlock();
    }
}
