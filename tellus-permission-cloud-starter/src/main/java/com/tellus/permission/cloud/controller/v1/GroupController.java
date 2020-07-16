package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractRelationController;
import com.tellus.permission.cloud.service.IGroupService;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.vo.create.CreateGroupVO;
import com.tellus.support.model.vo.result.GroupVO;
import com.tellus.support.model.vo.result.RoleVO;
import com.tellus.support.model.vo.result.UserVO;
import com.tellus.support.model.vo.retrieve.RetrieveGroupVO;
import com.tellus.support.model.vo.update.ReviseGroupRoleVO;
import com.tellus.support.model.vo.update.UpdateGroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * 组织管理
 *
 * @author Roy
 * @date 2020/7/16 16:54
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/groups")
@Api(tags = "组织管理")
public class GroupController extends AbstractRelationController<GroupVO, CreateGroupVO, RetrieveGroupVO, UpdateGroupVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IGroupService groupService;

    // ~ Constructors
    // ==============================================================================

    public GroupController(IGroupService groupService) {
        super(groupService);
        this.groupService = groupService;
    }

    // ~ Methods
    // ==============================================================================

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取组织下的用户")
    @ApiImplicitParam(name = "groupId", value = "组织ID", required = true, example = "1000", dataTypeClass = Integer.class)
    public Result<List<UserVO>> getUsers(@Valid @NotNull(message = "组织ID不能为空")
                                         @RequestParam(value = "groupId") Integer groupId) {
        //  数据权限校验
        checkWhetherSubordinate(groupId);
        return Result.success(groupService.findUsersByGroupId(groupId));
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取组织下的角色")
    @ApiImplicitParam(name = "groupId", value = "组织ID", required = true, example = "1000", dataTypeClass = Integer.class)
    public Result<List<RoleVO>> getRoles(@Valid @NotNull(message = "组织ID不能为空")
                                         @RequestParam(value = "groupId") Integer groupId) {
        //  数据权限校验
        checkWhetherSubordinate(groupId);
        return Result.success(groupService.findRolesByGroupId(groupId));
    }

    @IOperationLog(type = OperationTypeEnum.UPDATED, theme = "分配角色")
    @PutMapping(value = "/assign/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分配角色", notes = "为组织配置基本角色")
    public Result<Boolean> assignRoles(@Valid @RequestBody ReviseGroupRoleVO reviseGroupRoleVO) {

        Integer groupId = reviseGroupRoleVO.getGroupId();
        Set<Integer> roleIds = reviseGroupRoleVO.getRoleIds();

        //  数据权限校验
        checkWhetherSubordinate(groupId);
        reviseGroupRoleVO.getRoleIds()
                .forEach(roleId -> checkWhetherSubordinate(RelationTypeEnum.ROLE, roleId));

        return Result.success(groupService.reviseRoles(reviseGroupRoleVO));
    }


    // ~ Protected/Override Methods
    // ==============================================================================

    @Override
    protected RelationTypeEnum getRelationType() {
        return RelationTypeEnum.GROUP;
    }
}
