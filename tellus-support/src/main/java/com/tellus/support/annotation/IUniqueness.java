package com.tellus.support.annotation;

import com.tellus.support.enums.OperationTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 参数唯一性校验
 *
 * @author Roy
 * @date 2020/5/15 18:09
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IUniqueness {

    /**
     * @return 字段别名
     */
    @AliasFor
    String value() default "";

    /**
     * @return 非唯一提示信息
     */
    String message() default "该值唯一";

    /**
     * @return 字段映射前缀
     */
    String columnPrefix() default "";

    /**
     * @return 操作类型
     */
    OperationTypeEnum operationType() default OperationTypeEnum.CREATED;

}
