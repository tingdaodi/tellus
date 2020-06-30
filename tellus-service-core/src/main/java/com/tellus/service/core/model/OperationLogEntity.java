package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.enums.RequestMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志表 记录操作信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_operation_log")
public class OperationLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId("ID")
    private Integer id;

    /**
     * 操作主题 操作主题
     */
    @TableField("THEME")
    private String theme;

    /**
     * 操作人 操作人
     */
    @TableField("OPERATOR")
    private String operator;

    /**
     * 操作类型 1-查询，2-更新，3-新建，4-删除，5-逻辑删除
     */
    @TableField("OPERATE_TYPE")
    private OperationTypeEnum operateType;

    /**
     * 操作开始时间 操作开始时间
     */
    @TableField("OPERATION_BEGIN_TIME")
    private LocalDateTime operationBeginTime;

    /**
     * 操作结束时间 操作结束时间
     */
    @TableField("OPERATION_END_TIME")
    private LocalDateTime operationEndTime;

    /**
     * 传入参数 传入参数
     */
    @TableField("INCOMING_PARAMS")
    private String incomingParams;

    /**
     * 返回参数 返回参数
     */
    @TableField("RESULT_PARAMS")
    private String resultParams;

    /**
     * 是否成功 0-失败，1-成功
     */
    @TableField("SUCCESSFUL")
    private Boolean successful;

    /**
     * 异常信息 异常信息
     */
    @TableField("EXCEPTION")
    private String exception;

    /**
     * 客户端IP 客户端IP
     */
    @TableField("CLIENT_IP")
    private String clientIp;

    /**
     * 请求HOST 请求HOST
     */
    @TableField("REQUEST_HOST")
    private String requestHost;

    /**
     * 请求URL 请求URL
     */
    @TableField("REQUEST_URL")
    private String requestUrl;

    /**
     * 请求方式 :GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE
     */
    @TableField("REQUEST_METHOD")
    private RequestMethodEnum requestMethod;

    /**
     * 备注 备注
     */
    @TableField("REMARK")
    private String remark;

}
