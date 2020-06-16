package com.tellus.service.core.mapper;

import com.tellus.service.core.model.RoleResourceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色资源表 角色与资源关系 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface RoleResourceMapper extends BaseMapper<RoleResourceEntity> {

    /**
     * 根据角色 Id (及下级角色), 菜单 Id 删除角色菜单关系数据
     *
     * @param roleId      角色 Id
     * @param resourceIds 菜单 Ids
     * @return 受影响的行数
     */
    int deleteBatchIdsWithSubs(@Param("roleId") Integer roleId, @Param("resourceIds") List<Integer> resourceIds);

}
