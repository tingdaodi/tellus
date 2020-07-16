package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicRelationService;
import com.tellus.support.model.vo.create.CreateGroupVO;
import com.tellus.support.model.vo.result.GroupVO;
import com.tellus.support.model.vo.result.RoleVO;
import com.tellus.support.model.vo.result.UserVO;
import com.tellus.support.model.vo.retrieve.RetrieveGroupVO;
import com.tellus.support.model.vo.update.ReviseGroupRoleVO;
import com.tellus.support.model.vo.update.UpdateGroupVO;

import java.io.Serializable;
import java.util.List;

/**
 * 组织服务类
 *
 * @author Roy
 * @date 2020/7/10 16:43
 */
public interface IGroupService extends IBasicRelationService<GroupVO, CreateGroupVO, RetrieveGroupVO, UpdateGroupVO> {

    /**
     * 获取组织的用户
     *
     * @param groupId 组织ID
     * @return List<UserVO>
     */
    List<UserVO> findUsersByGroupId(Serializable groupId);

    /**
     * 获取组织的角色
     *
     * @param groupId 组织ID
     * @return List<RoleVO>
     */
    List<RoleVO> findRolesByGroupId(Serializable groupId);

    /**
     * 分配角色
     *
     * @param reviseGroupRoleVO VO
     * @return Boolean
     */
    Boolean reviseRoles(ReviseGroupRoleVO reviseGroupRoleVO);

}
