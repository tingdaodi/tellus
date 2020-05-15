package com.tellus.support.annotation;

import java.lang.annotation.*;

/**
 * 标识资源主键
 *
 * @author Roy
 * @date 2020/5/15 18:09
 * @see IUniqueness
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IPrimary {

}
