package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.SysPropertiesEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 系统配置表 系统运行依赖配置 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface SysPropertiesMapper extends BaseMapper<SysPropertiesEntity> {

    /**
     * 根据 Key 查询配置
     *
     * @param key 常量 key
     * @return SysPropertiesEntity
     */
    @Select({
            "<script>",
            "   select id, `key`, value, enabled, remark, creator, created_at, updated_by, updated_at from t_sys_properties ",
            "   where `key` = #{key} ",
            "</script> "
    })
    SysPropertiesEntity selectByKey(@Param("key") String key);

}
