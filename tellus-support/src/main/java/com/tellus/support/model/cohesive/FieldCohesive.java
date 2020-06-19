package com.tellus.support.model.cohesive;

import com.tellus.support.enums.DisplayModeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字段资源内聚 POJO
 *
 * @author Roy
 * @date 2020/6/19 11:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldCohesive implements Serializable {
    private static final long serialVersionUID = 5813597847844493910L;

    private Integer id;
    private String resourceId;
    private String method;
    private String label;
    private String name;
    private String type;
    private String enabled;
    private String remark;
    private DisplayModeEnum displayMode;

}
