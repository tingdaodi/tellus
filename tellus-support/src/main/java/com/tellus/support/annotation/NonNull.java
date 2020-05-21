package com.tellus.support.annotation;

import java.lang.annotation.*;

/**
 * 标记不可为空
 *
 * @author Roy
 * @date 2020/5/15 18:03
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NonNull {
}
