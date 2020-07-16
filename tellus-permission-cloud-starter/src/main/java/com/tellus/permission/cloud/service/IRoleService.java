package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.model.vo.create.CreateRoleVO;
import com.tellus.support.model.vo.result.FieldVO;
import com.tellus.support.model.vo.result.ResourceFieldVO;
import com.tellus.support.model.vo.result.ResourceVO;
import com.tellus.support.model.vo.result.RoleVO;
import com.tellus.support.model.vo.retrieve.RetrieveRoleVO;
import com.tellus.support.model.vo.update.UpdateRoleVO;

import java.io.Serializable;
import java.util.List;

/**
 * 角色服务类
 *
 * @author Roy
 * @date 2020/7/13 12:24
 */
public interface IRoleService extends IBasicRelationService<RoleVO,
        CreateRoleVO, RetrieveRoleVO, UpdateRoleVO> {

    /**
     * 查询角色拥有的字段
     *
     * @param roleId 角色ID
     * @return List<FieldVO>
     */
    List<FieldVO> findFieldsByRoleId(Serializable roleId);

    /**
     * 查询角色拥有的资源
     *
     * @param roleId 角色ID
     * @return List<ResourceVO>
     */
    List<ResourceFieldVO> findResourcesByRoleId(Serializable roleId);
}
