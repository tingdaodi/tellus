package com.tellus.permission.oauth2.service;

import com.tellus.permission.TellusPermissionProperties;
import com.tellus.permission.cloud.service.*;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.cohesive.UserDetailsCondenser;
import com.tellus.support.model.vo.create.CreateLoginLogVO;
import com.tellus.support.model.vo.result.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 默认授权实现
 *
 * @author Roy
 * @date 2020/7/31 18:08
 */
@Service("com.tellus.permission.oauth2.service.defaultAuthorizationService")
@ConditionalOnMissingBean(AuthorizationService.class)
public class DefaultAuthorizationServiceImpl implements AuthorizationService {

    // ~ Static fields/initializers
    // ==============================================================================

    private String rolePrefix = "ROLE_";
    private final WebMvcProperties webMvcProperties;
    private final IRoleService roleService;
    private final ISystemAuthService systemAuthService;
    private final ISystemUserService systemUserService;
    private final IResourceService resourceService;
    private final IGroupService groupService;
    private final TellusPermissionProperties tellusPermissionProperties;

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public DefaultAuthorizationServiceImpl(WebMvcProperties webMvcProperties,
                                           IRoleService roleService,
                                           ISystemAuthService systemAuthService,
                                           ISystemUserService systemUserService,
                                           IResourceService resourceService,
                                           IGroupService groupService,
                                           TellusPermissionProperties tellusPermissionProperties) {
        this.webMvcProperties = webMvcProperties;
        this.roleService = roleService;
        this.systemAuthService = systemAuthService;
        this.systemUserService = systemUserService;
        this.resourceService = resourceService;
        this.groupService = groupService;
        this.tellusPermissionProperties = tellusPermissionProperties;
    }

    // ~ Override Methods
    // ==============================================================================

    @Override
    public CustomizeUserDetails loadUserByUsername(String username, Set<String> platforms) {
        UserDetailsCondenser condenser = this.systemAuthService.findUserDetailByUsername(username);


        return null;
    }

    @Override
    public CustomizeUserDetails loadUserByUsername(String username) {
        return null;
    }

    @Override
    public List<ResourceVO> findAllResources() {
        return null;
    }

    @Override
    public Boolean isSubordinate(RelationTypeEnum relationType, Set<Integer> descendants) {
        return null;
    }

    @Override
    public Boolean loginSuccessAfter(CreateLoginLogVO createLoginLogVO) {
        return null;
    }


    // ~ Private Methods
    // ==============================================================================

}
