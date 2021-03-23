package com.tellus.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页VO
 *
 * @author Roy
 * @date 2020/5/18 18:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Pageable", description = "分页VO")
public class Pageable implements Serializable {
    private static final long serialVersionUID = 9205108190893523872L;

    @ApiModelProperty(name = "size", value = "每页条数", example = "默认：10")
    private Integer size = 10;

    @ApiModelProperty(name = "current", value = "当前页数", example = "默认：1")
    private Integer current = 1;

}
