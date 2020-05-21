package com.tellus.support;

import com.tellus.support.enums.OperationTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author Roy
 * @date 2020/5/18 18:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OperationLog", description = "操作日志")
public class OperationLog implements Serializable {
    private static final long serialVersionUID = 1921404650545213818L;

    private Integer id;

    private String theme;

    private String operator;

    private OperationTypeEnum operationType;

    private LocalDateTime operationBeginTime;

    private LocalDateTime operationEndTime;

    private String incomingParams;

    private String resultParams;

    private Boolean success;

    private String exception;

    private String clientIp;

    private String requestHost;

    private String requestUrl;

    private String requestMethod;

    private String remark;

}
