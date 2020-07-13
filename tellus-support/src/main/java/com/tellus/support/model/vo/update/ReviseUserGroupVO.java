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
 * 修改用户的组织VO
 *
 * @author Roy
 * @date 2020/7/13 13:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "修改用户的组织VO: 先删除已有的")
public class ReviseUserGroupVO implements Serializable {
    private static final long serialVersionUID = -8384618163849121225L;

    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, example = "user001")
    private String username;

    @NotNull(message = "组织ID不能为空")
    @ApiModelProperty(value = "组织ID", required = true, example = "[1,2,3,4]")
    private Set<Integer> groupIds;
}
