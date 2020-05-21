package com.tellus.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * 分页查询包装类
 *
 * @author Roy
 * @date 2020/5/18 18:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PageInfo", description = "分页查询VO")
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = -1997928550851868887L;

    @ApiModelProperty(name = "size", value = "每页条数", example = "默认： 10")
    private Integer size = 10;

    @ApiModelProperty(name = "current", value = "当前页数", example = "默认： 1")
    private Integer current = 1;

    @Valid
    @ApiModelProperty(name = "queries", value = "查询条件")
    private T queries;

    @ApiModelProperty(name = "asc", value = "升序排列数组")
    private String[] asc;

    @ApiModelProperty(name = "desc", value = "降序排列数组")
    private String[] desc;

    public PageInfo<T> set(Consumer<T> consumer) {
        if (null != this.queries) {
            consumer.accept(this.getQueries());
        }
        return this;
    }

}
