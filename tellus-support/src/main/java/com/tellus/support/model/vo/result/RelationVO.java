package com.tellus.support.model.vo.result;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tellus.support.enums.RelationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 层级关系VO
 *
 * @author Roy
 * @date 2020/6/30 23:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "层级关系VO")
public class RelationVO implements Serializable {

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
