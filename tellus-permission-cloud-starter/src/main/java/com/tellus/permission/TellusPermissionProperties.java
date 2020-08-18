package com.tellus.permission;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Tellus 授权/认证配置
 *
 * @author Roy
 * @date 2020/7/9 18:13
 */
@Data
@ConfigurationProperties(prefix = "tellus.permission")
public class TellusPermissionProperties {


}
