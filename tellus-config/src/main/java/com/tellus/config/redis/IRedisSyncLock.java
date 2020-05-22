package com.tellus.config.redis;

import java.lang.annotation.*;

/**
 * 同步锁注解
 *
 * @author Roy
 * @date 2020/5/21 14:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IRedisSyncLock {

    /**
     * 锁住的资源, Redis 的 Key
     * 支持 SpEL 表达式, root为不可变 Map
     * <p>
     * root 支持:
     * 1. args: #args[0] 参数列表
     * 2. 参数名: #user.name
     * 3. 方法名: #methodName
     * 4. d类: #targetClass
     */
    String value();

    /**
     * @return 持锁时间, 默认: 30 * 1000 (ms)
     */
    long keepMills() default 300_000;

    /**
     * @return 重试的间隔时间, 设置 {@code LockFailAction.RETRY}
     */
    long sleepMills() default 200;

    /**
     * @return 重试次数, 默认: 5次
     */
    int retryTimes() default 5;

    /**
     * @return 当获取锁失败后的动作
     */
    LockFailAction action() default LockFailAction.RETRY;

    /**
     * 当读取锁失败后的行为
     */
    enum LockFailAction {
        /**
         * 放弃
         */
        GIVE_UP,
        /**
         * 充值
         */
        RETRY
    }
}
