package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.model.vo.create.CreateRoleVO;
import com.tellus.support.model.vo.result.RoleVO;
import com.tellus.support.model.vo.retrieve.RetrieveRoleVO;
import com.tellus.support.model.vo.update.UpdateRoleVO;

/**
 * 角色服务类
 *
 * @author Roy
 * @date 2020/7/13 12:24
 */
public interface IRoleService extends IBasicRelationService<RoleVO,
        CreateRoleVO, RetrieveRoleVO, UpdateRoleVO> {
}
