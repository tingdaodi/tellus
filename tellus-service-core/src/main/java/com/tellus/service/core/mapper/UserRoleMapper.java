package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.UserRoleEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色表 用户与角色信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    /**
     * 根据角色 Id, 删除用户-角色关系
     *
     * @param roleIds 角色 Ids
     * @return int 受影响的行数
     */
    @Delete({
            "<script>",
            "delete from t_user_role where role_id in ",
            "  <foreach collection='roleIds' index='index' item='roleId' open='(' separator=',' close=')'> ",
            "     #{roleId} ",
            "  </foreach> ",
            "</script>"
    })
    int deleteBatchRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据用户 Id, 删除用户-角色关系
     *
     * @param userIds 用户 Ids
     * @return int
     */
    @Delete({
            "<script>",
            "delete from t_user_role where user_id in ",
            "  <foreach collection='userIds' index='index' item='userId' open='(' separator=',' close=')'> ",
            "     #{userId} ",
            "  </foreach> ",
            "</script>"
    })
    int deleteBatchUserIds(@Param("userIds") List<Integer> userIds);

}
