package com.tellus.service.core.richer;

import com.tellus.support.enums.RelationTypeEnum;

import java.lang.annotation.*;

/**
 * 充血模型
 *
 * @author Roy
 * @date 2020/7/1 1:25
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IRicher {

    /**
     * 关系类型
     *
     * @return 关系类型
     */
    RelationTypeEnum value();
}
