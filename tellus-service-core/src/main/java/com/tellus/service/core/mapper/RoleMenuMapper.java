package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.RoleMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 角色与菜单关系 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {

    /**
     * 根据角色 Id (及下级角色), 菜单 Id 删除角色菜单关系数据
     *
     * @param roleId  角色 Id
     * @param menuIds 菜单 Ids
     * @return 受影响的行数
     */
    int deleteBatchIdsWithSubs(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

}
