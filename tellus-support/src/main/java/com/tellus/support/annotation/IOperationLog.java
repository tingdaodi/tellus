package com.tellus.support.annotation;

import com.tellus.support.enums.OperationTypeEnum;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * <p>
 * 使用此注解标注方法，在方法前、后，或者异常将操作信息持久化
 *
 * @author Roy
 * @date 2020/5/15 18:05
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IOperationLog {

    /**
     * @return 操作类型
     */
    OperationTypeEnum type() default OperationTypeEnum.RETRIEVED;

    /**
     * @return 操作主题
     */
    String theme() default "";

    /**
     * @return 操作人
     */
    String operator() default "System";

    /**
     * @return 非持久化；默认：false，持久化
     * {@code tellus.operation.endurance}
     */
    boolean indefinite() default false;

    /**
     * @return 环绕前日志
     */
    boolean before() default true;

    /**
     * @return 环绕后日志
     */
    boolean after() default true;

    /**
     * @return 异常日志
     */
    boolean afterThrowing() default true;


    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Exclusion {
        //  日志输出时，排除此字段
    }

}
