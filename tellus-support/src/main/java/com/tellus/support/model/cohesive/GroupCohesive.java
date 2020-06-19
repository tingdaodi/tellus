package com.tellus.support.model.cohesive;

import com.tellus.support.interfaces.ISubordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 组织内聚 POJO
 *
 * @author Roy
 * @date 2020/6/19 11:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCohesive implements ISubordinate, Serializable {
    private static final long serialVersionUID = -5474286678868737901L;

    private Integer groupId;
    private String groupName;
    private String groupCode;
    private String groupEnabled;
    private String groupRemark;
    private Integer groupParentId;

    @Override
    public Integer getParentId() {
        return groupParentId;
    }

    @Override
    public Integer getId() {
        return groupId;
    }
}
