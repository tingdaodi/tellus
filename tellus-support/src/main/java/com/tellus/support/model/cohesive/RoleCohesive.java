package com.tellus.support.model.cohesive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色内聚 POJO
 *
 * @author Roy
 * @date 2020/6/19 12:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleCohesive implements Serializable {
    private static final long serialVersionUID = -3365785026980837497L;

    private Integer id;
    private String name;
    private String role;
    private String remark;
    private Integer enabled;

}
