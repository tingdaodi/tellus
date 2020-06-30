package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.tellus.support.enums.RelationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 层级关系表 层级关系
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_relation")
public class RelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关系类型 1-用户，2-组织，3-角色，4-菜单
     */
    @TableId("TYPE")
    private RelationTypeEnum type;

    /**
     * 祖先，上级ID 祖先ID
     */
    @TableField("ANCESTOR")
    private Integer ancestor;

    /**
     * 后代，下级ID 后代ID
     */
    @TableField("DESCENDANT")
    private Integer descendant;

    /**
     * 隔代，祖先与后代的距离 祖先与后代的隔代距离
     */
    @TableField("DISTANCE")
    private Integer distance;

}
