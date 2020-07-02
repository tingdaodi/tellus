package com.tellus.support.model.vo.update;

import com.tellus.support.enums.RelationChangeModeEnum;
import com.tellus.support.enums.RelationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 移动层级关系VO
 *
 * @author Roy
 * @date 2020/7/1 18:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "移动层级关系VO")
public class MoveRelationVO implements Serializable {
    private static final long serialVersionUID = -2148944064836904557L;

    @NotNull(message = "关系类型不能为空")
    @ApiModelProperty(value = "关系类型", example = "1-用户，2-组织，3-角色，4-资源, 5-菜单", allowableValues = "1,2,3,4,5")
    private RelationTypeEnum relationType;

    @NotNull(message = "变更模式不能为空")
    @ApiModelProperty(value = "变更模式", example = "1-节点, 2-分支", allowableValues = "1,2")
    private RelationChangeModeEnum changeMode;

    @NotNull(message = "上级Id不能为空")
    @ApiModelProperty(value = "上级Id", example = "1000")
    private Integer ancestor;

    @NotNull(message = "下级Id不能为空")
    @ApiModelProperty(value = "下级Id", example = "1000")
    private Integer descendant;

    @ApiModelProperty(value = "关系生效时间", example = "2020-07-01 00:00:00")
    private LocalDateTime effectiveDate;

    @ApiModelProperty(value = "创建人", example = "system")
    private String creator;

    @ApiModelProperty(value = "创建时间", example = "2020-07-01 00:00:00")
    private LocalDateTime createdAt;

}