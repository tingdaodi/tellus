package com.tellus.config.redis;

/**
 * 锁接口
 *
 * @author Roy
 * @date 2020/5/21 14:35
 */
public interface IDistributedLock {

    long TIMEOUT_MILLIS = 300_000;

    int RETRY_TIMES = 5;

    int SLEEP_MILLS = 500;

    /**
     * 加锁
     *
     * @param key KEY
     * @return boolean
     */
    default boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLS);
    }

    /**
     * 加锁
     *
     * @param key        KEY
     * @param retryTimes 重试次数
     * @return boolean
     */
    default boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLS);
    }

    /**
     * 加锁
     *
     * @param key        KEY
     * @param retryTimes 重试次数
     * @param sleepMills 睡眠时间
     * @return boolean
     */
    default boolean lock(String key, int retryTimes, long sleepMills) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMills);
    }

    /**
     * 加锁
     *
     * @param key    KEY
     * @param expire 过期时间
     * @return boolean
     */
    default boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLS);
    }

    /**
     * 加锁
     *
     * @param key        KEY
     * @param expire     过期时间
     * @param retryTimes 重试次数
     * @return boolean
     */
    default boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLS);
    }

    /**
     * 加锁
     *
     * @param key        KEY
     * @param expire     过期时间
     * @param retryTimes 重试次数
     * @param sleepMills 睡眠时间
     * @return boolean
     */
    boolean lock(String key, long expire, int retryTimes, long sleepMills);

    /**
     * 释放锁
     *
     * @param key KEY
     * @return boolean
     */
    boolean releaseLock(String key);
}
