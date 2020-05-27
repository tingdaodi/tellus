package com.tellus.service.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 操作日志表 记录操作信息
 * </p>
 *
 * @author Roy.l
 * @since 2020-05-25
 */
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
    private Integer operateType;

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
    private Integer successful;

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
    private Integer requestMethod;

    /**
     * 备注 备注
     */
    @TableField("REMARK")
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public LocalDateTime getOperationBeginTime() {
        return operationBeginTime;
    }

    public void setOperationBeginTime(LocalDateTime operationBeginTime) {
        this.operationBeginTime = operationBeginTime;
    }

    public LocalDateTime getOperationEndTime() {
        return operationEndTime;
    }

    public void setOperationEndTime(LocalDateTime operationEndTime) {
        this.operationEndTime = operationEndTime;
    }

    public String getIncomingParams() {
        return incomingParams;
    }

    public void setIncomingParams(String incomingParams) {
        this.incomingParams = incomingParams;
    }

    public String getResultParams() {
        return resultParams;
    }

    public void setResultParams(String resultParams) {
        this.resultParams = resultParams;
    }

    public Integer getSuccessful() {
        return successful;
    }

    public void setSuccessful(Integer successful) {
        this.successful = successful;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getRequestHost() {
        return requestHost;
    }

    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Integer getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(Integer requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OperationLogEntity{" +
                "id=" + id +
                ", theme=" + theme +
                ", operator=" + operator +
                ", operateType=" + operateType +
                ", operationBeginTime=" + operationBeginTime +
                ", operationEndTime=" + operationEndTime +
                ", incomingParams=" + incomingParams +
                ", resultParams=" + resultParams +
                ", successful=" + successful +
                ", exception=" + exception +
                ", clientIp=" + clientIp +
                ", requestHost=" + requestHost +
                ", requestUrl=" + requestUrl +
                ", requestMethod=" + requestMethod +
                ", remark=" + remark +
                "}";
    }
}
