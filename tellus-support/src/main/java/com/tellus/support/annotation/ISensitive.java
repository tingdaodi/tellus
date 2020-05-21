package com.tellus.support.annotation;

import com.tellus.support.enums.SensitiveTypeEnum;

import java.lang.annotation.*;

/**
 * 标记敏感字段加解密处理
 *
 * @author Roy
 * @date 2020/5/15 18:05
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ISensitive {

    /**
     * @return 加密字段
     */
    SensitiveTypeEnum value() default SensitiveTypeEnum.GENERAL;

}
