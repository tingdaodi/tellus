package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.RoleFieldEntity;
import com.tellus.support.enums.DisplayModeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色字段表 角色与字段关系 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface RoleFieldService extends ICustomizeService<RoleFieldEntity> {

    /**
     * 批量删除角色-字段关系数据, (下级角色同步删除)
     *
     * @param roleId   角色ID
     * @param fieldIds 字段IDs
     * @return 受影响的行数
     */
    Integer removeBatchWithSubs(Integer roleId, @Param("fieldIds") List<Integer> fieldIds);

    /**
     * 批量更新角色-字段关系数据, (下级角色同步修改)
     *
     * @param entities 角色-字段关系集合
     * @return 受影响的行数
     */
    Integer updateBatchWithSubs(List<RoleFieldEntity> entities);

    /**
     * 查询角色及其所有下级角色拥有的字段关系数据 (包含 #{ancestor})
     *
     * @param roleIds 上级角色ID
     * @return 角色-字段关系
     */
    List<RoleFieldEntity> findSubs(List<Integer> roleIds);


    /**
     * 更变角色拥有的字段资源
     * 先删除之前的关系数据, 再添加新的角色-字段资源关系数据
     *
     * @param roleId 角色 Id
     * @param fields 字段信息
     * @return
     */
    boolean changeRoleFieldWithSubs(Integer roleId, Map<Integer, DisplayModeEnum> fields);

}
