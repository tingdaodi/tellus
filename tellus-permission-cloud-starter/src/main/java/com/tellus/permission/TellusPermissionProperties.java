package com.tellus.permission;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Tellus 授权/认证配置
 *
 * @author Roy
 * @date 2020/7/9 18:13
 */
@Data
@ConfigurationProperties(prefix = "tellus.permission")
public class TellusPermissionProperties {

    /**
     * VO 模型包目录
     * 出/入参数， 过滤字段策略中，需要指定接口使用的VO路径
     */
    private List<String> modelPackages;

}
