package com.tellus.support.annotation;

import java.lang.annotation.*;

/**
 * 获取客户端信息
 * <p>
 * {@code Result say(@GetRequestInfo RequestInfo requestInfo}
 *
 * @author Roy
 * @date 2020/5/15 18:03
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetRequestInfo {
}
