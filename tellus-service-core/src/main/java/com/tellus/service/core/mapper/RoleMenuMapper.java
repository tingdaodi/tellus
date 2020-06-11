package com.tellus.service.core.mapper;

import com.tellus.service.core.model.RoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    int deleteRoleMenuWithSubs(@Param("roleId") Integer roleId, @Param("resourceIds") List<Integer> resourceIds);

}
