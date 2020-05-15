package com.tellus.support.annotation;

import org.apache.ibatis.annotations.ResultType;

import java.lang.annotation.*;

/**
 * 构建查询条件
 *
 * @author Roy
 * @date 2020/5/15 18:03
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IQueries {

    /**
     * @return 查询条件映射字段
     */
    String value() default "";


}
