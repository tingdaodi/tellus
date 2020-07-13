package com.tellus.support.model.vo.retrieve;

import com.tellus.support.annotation.IQueries;
import com.tellus.support.enums.RelationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询层级关系VO
 *
 * @author Roy
 * @date 2020/6/30 23:46
 */
@IQueries
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询层级关系VO")
public class RetrieveRelationVO implements Serializable {

    private static final long serialVersionUID = -7123067836621971524L;

    @ApiModelProperty(value = "关系类型", example = "1-用户，2-组织，3-角色，4-菜单", allowableValues = "1,2,3,4")
    private RelationTypeEnum type;

    @ApiModelProperty(value = "上级ID", example = "1000")
    private Integer ancestor;

    @ApiModelProperty(value = "下级ID", example = "1000")
    private Integer descendant;

    @ApiModelProperty(value = "层级", example = "1")
    private Integer distance;

}
