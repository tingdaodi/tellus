package com.tellus.support.model.cohesive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 平台内聚 POJO
 *
 * @author Roy
 * @date 2020/6/19 11:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformCohesive implements Serializable {
    private static final long serialVersionUID = 3883426790613531415L;

    private Integer platformId;
    private String platformName;
    private String platformCode;
    private Integer platformEnabled;

}
