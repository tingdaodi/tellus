package com.tellus.config.operationlog;

import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.Principal;
import java.util.Set;

/**
 * 操作日志配置
 *
 * @author Roy
 * @date 2020/5/19 14:10
 */
@Data
@ConfigurationProperties(prefix = "tellus.operation")
public class OperationLogProperties {

    private boolean enabled = true;

    private boolean endurance = true;

    private boolean before = true;

    private boolean after = true;

    private boolean afterThrowing = true;

    private Set<String> sensitives;
    private Set<String> sensitiveClasses = Sets.newHashSet(Principal.class.getName());

}
