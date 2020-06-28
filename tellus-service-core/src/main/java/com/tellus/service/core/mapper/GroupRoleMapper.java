package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.GroupRoleEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织角色表 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-06-06
 */
public interface GroupRoleMapper extends BaseMapper<GroupRoleEntity> {

    /**
     * 根据组织 Id, 删除组织-角色关系
     *
     * @param groupIds 组织 Ids
     * @return int 受影响的行数
     */
    @Delete({
            "<script>",
            "delete from t_group_role where group_id in ",
            "  <foreach collection='groupIds' index='index' item='groupId' open='(' separator=',' close=')'> ",
            "     #{groupId} ",
            "  </foreach>",
            "</script>"
    })
    int deleteBatchGroupIds(@Param("groupIds") List<Integer> groupIds);

    /**
     * 根据角色 Id, 删除组织-角色关系
     *
     * @param roleIds 角色 Ids
     * @return int 受影响的行数
     */
    @Delete({
            "<script>",
            "delete from t_group_role where group_id in ",
            "  <foreach collection='roleIds' index='index' item='roleId' open='(' separator=',' close=')'> ",
            "     #{roleId} ",
            "  </foreach>",
            "</script>"
    })
    int deleteBatchRoleIds(@Param("roleIds") List<Integer> roleIds);

}
