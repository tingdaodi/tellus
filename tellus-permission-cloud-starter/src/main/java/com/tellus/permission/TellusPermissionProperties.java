package com.tellus.permission;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Set;

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

    /**
     * 初始化字段资源数据， 默认只生成查询接口的出、入参数
     * 其它接口，特例处理，配置此字段
     * 注：该值为资源值；格式： PUT /domains
     */
    private Set<String> initFieldSpecialResources;


}
