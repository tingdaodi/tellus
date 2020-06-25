package com.tellus.support.annotation;

import java.lang.annotation.*;

/**
 * Controller 层, 注解获取用户信息
 *
 * @author Roy
 * @date 2020/6/23 13:42
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetUserInfo {

    /**
     * 用户名 (优先级低)
     * <p>
     * UserInfoService.getUserInfo() > this.username()
     *
     * @return 用户名
     */
    String username() default "";

    /**
     * 支持查询, 默认: 不支持
     *
     * @return true 需要自定义实现获取用户信息接口
     */
    boolean supported() default false;
}
