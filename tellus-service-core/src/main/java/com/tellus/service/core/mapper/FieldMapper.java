package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.FieldEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字段表 字段信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface FieldMapper extends BaseMapper<FieldEntity> {

    /**
     * 查询属于角色的字段信息 (包含下级角色)
     *
     * @param roleIds 角色 Ids
     * @return 字段信息
     */
    List<FieldEntity> selectSubsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 查询角色下某个资源的字段数据 (包含下级角色)
     *
     * @param resourceId 资源 Id
     * @param roleIds    角色 Ids
     * @return 资源数据
     */
    List<FieldEntity> selectSubsByRoleIdsOfResource(@Param("resourceId") Integer resourceId,
                                                    @Param("roleIds") List<Integer> roleIds);

}
