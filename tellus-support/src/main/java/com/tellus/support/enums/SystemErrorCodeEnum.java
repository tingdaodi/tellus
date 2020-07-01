package com.tellus.support.enums;

import com.tellus.support.interfaces.IErrorCode;
import lombok.Getter;

/**
 * 系统错误码
 *
 * @author Roy
 * @date 2020/5/15 22:59
 */
@Getter
public enum SystemErrorCodeEnum implements IErrorCode {

    /**
     * 数据格式错误
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * TOKEN 过期，或者未认证
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 无权限访问资源
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * 未找到资源
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 不能访问的方法
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * 服务器正忙
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * 值唯一
     */
    UNIQUENESS(1000, "%s已存在"),

    // ~ 登录 2000 ~ 2099
    // ==============================================================================


    // ~ 操作日志 2100 ~ 2199
    // ==============================================================================


    // ~ 组织管理 2200 ~ 2299
    // ==============================================================================


    // ~ 用户管理 2300 ~ 2399
    // ==============================================================================


    // ~ 系统配置 2400 ~ 2499
    // ==============================================================================


    // ~ 角色管理 2500 ~ 2599
    // ==============================================================================


    // ~ 角色管理 2600 ~ 2699
    // ==============================================================================


    // ~ 移动节点 2700 ~ 2799
    // ==============================================================================

    RELATION_MOVE_TARGET_NODE_SAME(2700, "目标节点与当前节点相同"),
    RELATION_SAVE_NODE_EXISTS(2701, "节点已存在"),
    RELATION_NOT_MOVE_ROOT(2702, "根节点不能移动"),
    RELATION_NOT_SUPPORTED_MOVE_NODE(2703, "不支持移动节点"),
    RELATION_NOT_SUPPORTED_MOVE_TO_SUB(2704, "不支持移动至下级节点"),
    ;

    private final Integer code;
    private final String description;

    SystemErrorCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
