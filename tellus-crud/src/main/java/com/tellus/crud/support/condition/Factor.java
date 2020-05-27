package com.tellus.crud.support.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tellus.support.enums.basic.OptionType;

/**
 * 通用条件因子接口
 *
 * @author Roy
 * @date 2020/5/25 23:58
 */
public interface Factor {

    /**
     * 设置字段名
     *
     * @param fieldName 字段名
     */
    void setFieldName(String fieldName);

    /**
     * 设置操作符类型
     *
     * @param optionType 操作符类型
     */
    void setOptionType(OptionType optionType);

    /**
     * 设置属性值
     *
     * @param value 属性值
     */
    void setValue(Object value);

    /**
     * 设置 wrapper
     *
     * @param wrapper 条件对象
     * @param cls     实体
     * @param <T>     泛型 Entity
     */
    <T> void handle(QueryWrapper<T> wrapper, Class<T> cls);

    /**
     * 是否支持的操作符
     *
     * @param optionType 操作符
     * @return boolean
     */
    boolean supported(OptionType optionType);

}
