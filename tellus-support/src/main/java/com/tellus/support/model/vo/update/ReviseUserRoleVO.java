package com.tellus.support.model.vo.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 修改用户的角色VO
 *
 * @author Roy
 * @date 2020/7/13 13:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "修改用户的角色VO: 先删除已有的")
public class ReviseUserRoleVO implements Serializable {
    private static final long serialVersionUID = -8384618163849121225L;

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true, example = "1000")
    private Integer userId;

    @NotNull(message = "角色ID不能为空")
    @ApiModelProperty(value = "角色ID", required = true, example = "[1,2,3,4]")
    private Set<Integer> roleIds;
}
