package com.tellus.crud.support;

import com.baomidou.mybatisplus.annotation.TableId;
import com.tellus.support.annotation.IPrimary;
import com.tellus.toolkit.ReflectionKit;

/**
 * 与 Entity 相关的工具
 *
 * @author Roy
 * @date 2020/5/25 21:23
 */
public class EntityUtil {

    /**
     * 获取主键的值
     *
     * @param entity 实体
     * @return Integer
     */
    public static Integer getTableId(Object entity) {
        return (Integer) ReflectionKit.getMethodValue(entity, TableId.class);
    }

    /**
     * 获取主键的值
     *
     * @param entity 实体
     * @return 主键值
     */
    public static Object getPrimaryKey(Object entity) {
        return ReflectionKit.getMethodValue(entity, IPrimary.class);
    }

}
