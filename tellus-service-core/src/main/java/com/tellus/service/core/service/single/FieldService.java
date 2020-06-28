package com.tellus.service.core.service.single;

import com.tellus.crud.service.ICustomizeService;
import com.tellus.service.core.model.FieldEntity;

import java.util.List;

/**
 * <p>
 * 字段表 字段信息 服务类
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-29
 */
public interface FieldService extends ICustomizeService<FieldEntity> {


    /**
     * 查询属于角色的字段信息 (包含下级角色)
     *
     * @param roleIds 角色 Ids
     * @return 字段信息
     */
    List<FieldEntity> findSubsByRoleIds(List<Integer> roleIds);

    /**
     * 查询角色下某个资源的字段数据 (包含下级角色)
     *
     * @param resourceId 资源 Id
     * @param roleIds    角色 Ids
     * @return 资源数据
     */
    List<FieldEntity> findSubsByRoleIdsOfResource(Integer resourceId, List<Integer> roleIds);
}
