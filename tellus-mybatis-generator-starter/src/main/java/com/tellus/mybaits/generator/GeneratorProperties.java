package com.tellus.mybaits.generator;

import lombok.Data;

/**
 * Mybatis-plus generator 配置
 *
 * @author Roy
 * @date 2020/5/25 12:42
 */
@Data
public class GeneratorProperties {

    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;

}
