package com.tellus.support.annotation;

import com.tellus.support.enums.basic.FactorType;
import com.tellus.support.enums.basic.OptionType;

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

    /**
     * @return 构建条件因子类型
     */
    FactorType type() default FactorType.STRING;

    /**
     * @return 条件操作符
     */
    OptionType option() default OptionType.EQ;

    /**
     * @return 排除字段构建条件
     */
    boolean exclude() default false;
}
