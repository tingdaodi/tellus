package com.tellus.crud;

import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * Tellus 通用 CRUD 基础模块启动配置
 *
 * @author Roy
 * @date 2020/5/25 21:13
 */
@Data
@ConfigurationProperties(prefix = "tellus.crud")
public class TellusCrudProperties {

    /**
     * 敏感数据加解密启用
     */
    private boolean sensitiveEnable = true;
    /**
     * 敏感数据加解密原始KEY
     */
    private String sensitiveSecret = "M5BskhTL3PkuuGjOu5LqNMCsEHdCMOD";
    /**
     * 加减密-敏感字段
     */
    private Set<String> sensitiveCryptFields = Sets.newHashSet("phone", "email");

}
