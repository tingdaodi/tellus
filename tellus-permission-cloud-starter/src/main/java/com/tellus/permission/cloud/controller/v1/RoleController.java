package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractRelationController;
import com.tellus.permission.cloud.service.IRoleService;
import com.tellus.support.Result;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.model.vo.create.CreateRoleVO;
import com.tellus.support.model.vo.result.FieldVO;
import com.tellus.support.model.vo.result.ResourceFieldVO;
import com.tellus.support.model.vo.result.RoleVO;
import com.tellus.support.model.vo.retrieve.RetrieveRoleVO;
import com.tellus.support.model.vo.update.UpdateRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色管理
 *
 * @author Roy
 * @date 2020/7/16 20:46
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/roles")
@Api(tags = "角色管理")
public class RoleController extends AbstractRelationController<RoleVO, CreateRoleVO, RetrieveRoleVO, UpdateRoleVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IRoleService roleService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public RoleController(IRoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }


    // ~ Main Methods
    // ==============================================================================

    @GetMapping(value = "/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取角色拥有的字段")
    @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, example = "1000", dataTypeClass = Integer.class)
    public Result<List<FieldVO>> getFields(@Valid @NotNull(message = "角色ID不能为空")
                                           @RequestParam(value = "roleId") Integer roleId) {
        checkWhetherSubordinate(roleId);
        List<FieldVO> fields = roleService.findFieldsByRoleId(roleId);
        return Result.success(fields);
    }

    @GetMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取角色拥有的资源 (包含字段)")
    @ApiImplicitParam(name = "resourceId", value = "角色ID", required = true, example = "1000", dataTypeClass = Integer.class)
    public Result<List<ResourceFieldVO>> getResources(@Valid @NotNull(message = "角色ID不能为空")
                                                      @RequestParam(value = "roleId") Integer roleId) {
        checkWhetherSubordinate(roleId);
        List<ResourceFieldVO> resources = roleService.findResourcesByRoleId(roleId);
        return Result.success(resources);
    }

    // ~ Protected Methods
    // ==============================================================================


    // ~ Override Methods
    // ==============================================================================

    @Override
    protected RelationTypeEnum getRelationType() {
        return null;
    }

    // ~ Private Methods
    // ==============================================================================
}
