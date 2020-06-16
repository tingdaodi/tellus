package com.tellus.service.core.mapper;

import com.tellus.service.core.BaseRelationMapper;
import com.tellus.service.core.model.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 菜单表 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface MenuMapper extends BaseRelationMapper<MenuEntity> {

    /**
     * 根据用户Id, 查询菜单
     *
     * @param userId 用户Id
     * @return List<MenuEntity>
     */
    List<MenuEntity> selectByUserId(@Param("userId") int userId);

    /**
     * 根据角色Id, 查询菜单
     *
     * @param roleIds 角色 Ids
     * @return List<MenuEntity>
     */
    List<MenuEntity> selectByRoleIds(@Param("roleIds") List<Integer> roleIds);

}
