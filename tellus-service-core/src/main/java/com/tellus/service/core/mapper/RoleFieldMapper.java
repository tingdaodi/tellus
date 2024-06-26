package com.tellus.service.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tellus.service.core.model.RoleFieldEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色字段表 角色与字段关系 Mapper 接口
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
public interface RoleFieldMapper extends BaseMapper<RoleFieldEntity> {

    /**
     * 批量删除角色-字段关系数据, (下级角色同步删除)
     *
     * @param roleId   角色ID
     * @param fieldIds 字段IDs
     * @return 受影响的行数
     */
    int deleteBatchWithSubs(@Param("roleId") int roleId, @Param("fieldIds") List<Integer> fieldIds);

    /**
     * 批量更新角色-字段关系数据, (下级角色同步修改)
     *
     * @param entities 角色-字段关系集合
     * @return 受影响的行数
     */
    int updateBatchWithSubs(@Param("entities") List<RoleFieldEntity> entities);

    /**
     * 查询角色及其所有下级角色拥有的字段关系数据 (包含 #{ancestor})
     *
     *
     * @param roleIds 上级角色ID
     * @return 角色-字段关系
     */
    List<RoleFieldEntity> selectSubs(@Param("roleIds") List<Integer> roleIds);

}
