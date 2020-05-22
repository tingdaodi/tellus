package com.tellus.config.redis;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分布式锁定义切面
 *
 * @author Roy
 * @date 2020/5/21 15:42
 */
@Slf4j
@Aspect
@Component
@ConditionalOnBean(IDistributedLock.class)
public class DistributedLockAspect {

    private static final Pattern IS_EXPRESSION = Pattern.compile("#\\{(.*)}");

    private final IDistributedLock distributedLock;

    @Autowired
    public DistributedLockAspect(IDistributedLock distributedLock) {
        this.distributedLock = distributedLock;
    }

    @Around(value = "@annotation(redisSyncLock)")
    public Object around(ProceedingJoinPoint point, IRedisSyncLock redisSyncLock) throws Throwable {

        String key = parserKey(point, redisSyncLock.value());
        if (Strings.isNullOrEmpty(key)) {
            Object[] args = point.getArgs();
            key = Arrays.toString(args);
        }

        log.debug("key:{} try get lock", key);

        int retryTimes = redisSyncLock.action().equals(IRedisSyncLock.LockFailAction.RETRY)
                ? redisSyncLock.retryTimes() : 0;
        boolean lock = distributedLock.lock(key, redisSyncLock.keepMills(), retryTimes, redisSyncLock.sleepMills());
        if (!lock) {
            log.warn("obtain lock failed, key:{}", key);
            throw new DistributedLockException(String.format("obtain lock failed, key:%s", key));
        }

        try {
            log.debug("obtain lock success, key:{}", key);
            return point.proceed();
        } finally {
            boolean result = distributedLock.releaseLock(key);
            log.debug("release lock, key:{} {}", key, result ? "success" : "failed");
        }

    }

    private String parserKey(ProceedingJoinPoint point, String expression) {
        if (Strings.isNullOrEmpty(expression)) {
            return expression;
        }
        Matcher matcher = IS_EXPRESSION.matcher(expression);
        if (matcher.find()) {
            String value = matcher.group(1).trim();
            if (!Strings.isNullOrEmpty(value)) {
                @SuppressWarnings("DuplicatedCode")
                Expression exp = new SpelExpressionParser().parseExpression(value);
                Signature signature = point.getSignature();
                MethodSignature methodSignature = (MethodSignature) signature;
                String[] names = methodSignature.getParameterNames();
                Object[] args = point.getArgs();
                StandardEvaluationContext context = new StandardEvaluationContext();
                context.setVariable("args", args);
                context.setVariable("methodName", methodSignature.getName());
                context.setVariable("targetClass", point.getTarget().getClass());
                for (int i = 0; i < names.length; i++) {
                    context.setVariable(names[i], args[i]);
                }
                return exp.getValue(context, String.class);
            }
        }
        return expression;
    }
}
