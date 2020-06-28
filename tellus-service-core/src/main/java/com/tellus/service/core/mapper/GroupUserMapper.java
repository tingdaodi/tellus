package com.tellus.service.core.mapper;

import com.tellus.service.core.model.GroupUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织用户表 组织与用户关系信息 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface GroupUserMapper extends BaseMapper<GroupUserEntity> {

    /**
     * 根据组织 Id, 删除组织-用户关系
     *
     * @param groupIds 组织 Ids
     * @return int 受影响的行数
     */
    @Delete({
            "<script>",
            "delete from t_group_user where group_id in ",
            "  <foreach collection='groupIds' index='index' item='groupId' open='(' separator=',' close=')'> ",
            "     #{groupId} ",
            "  </foreach> ",
            "</script>"
    })
    int deleteBatchGroupIds(@Param("groupIds") List<Integer> groupIds);

    /**
     * 根据用户 Id, 删除组织-用户关系
     *
     * @param userIds 组织 Ids
     * @return int
     */
    @Delete({
            "<script>",
            "delete from t_group_user where group_id in ",
            "  <foreach collection='userIds' index='index' item='userId' open='(' separator=',' close=')'> ",
            "     #{userId} ",
            "  </foreach> ",
            "</script>"
    })
    int deleteBatchUserIds(@Param("userIds") List<Integer> userIds);

}
