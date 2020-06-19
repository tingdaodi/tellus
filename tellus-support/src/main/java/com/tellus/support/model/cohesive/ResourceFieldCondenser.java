package com.tellus.support.model.cohesive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源-字段聚合 POJO
 *
 * @author Roy
 * @date 2020/6/19 15:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceFieldCondenser implements Serializable {
    private static final long serialVersionUID = 4048477980599749048L;

    private Integer id;
    private String name;
    private String value;
    private Integer enabled;
    private String remark;
    private Integer sort;
    private String creator;
    private LocalDateTime createdAt;
    private String updater;
    private LocalDateTime updatedAt;
    private Integer parentId;

}
