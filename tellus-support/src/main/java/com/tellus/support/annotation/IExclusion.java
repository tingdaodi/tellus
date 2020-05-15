package com.tellus.support.annotation;

import java.lang.annotation.*;

/**
 * GSON 过滤字段
 *
 * @author Roy
 * @date 2020/5/15 18:06
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IExclusion {

    /**
     * 优先级最高
     *
     * @return 是否排除字段（禁止此字段序列化）
     */
    boolean value() default true;

}
