package com.tellus.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 返回分页查询包装类
 *
 * @author Roy
 * @date 2020/5/18 18:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PageWrapper", description = "分页返回Wrapper")
public class PageWrapper<T> implements Serializable {

    private static final long serialVersionUID = 3085882413689948188L;

    @ApiModelProperty(name = "total", value = "总数", example = "100")
    private int total;

    @ApiModelProperty(name = "size", value = "每页条数", example = "10")
    private int size;

    @ApiModelProperty(name = "current", value = "当前页数", example = "1")
    private int current;

    @ApiModelProperty(name = "currentSize", value = "当前页总数", example = "10")
    private int currentSize;

    @ApiModelProperty(name = "queries", value = "查询条件")
    private List<T> records = Collections.emptyList();

    public PageWrapper(final long total, final long size, final long current, final List<T> records) {
        this.total = (int) total;
        if (0 != total) {
            this.currentSize = records.size();
        }
        this.size = (int) size;
        this.current = (int) current;
        this.records = records;
    }
}
