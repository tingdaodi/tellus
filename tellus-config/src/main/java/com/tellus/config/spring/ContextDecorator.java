package com.tellus.config.spring;

import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 用于任务的调用时设置一些执行上下文，或者为任务执行提供一些监视/统计
 *
 * @author Roy
 * @date 2020/5/22 14:10
 */
public class ContextDecorator implements TaskDecorator {

    @Override
    @NonNull
    public Runnable decorate(@NonNull Runnable runnable) {
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        return () -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                runnable.run();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}
