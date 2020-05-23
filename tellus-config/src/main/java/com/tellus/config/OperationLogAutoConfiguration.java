package com.tellus.config;

import com.tellus.config.operationlog.OperationLogAspect;
import com.tellus.config.operationlog.OperationLogProperties;
import com.tellus.config.operationlog.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 操作日志自动装配类
 *
 * @author Roy
 * @date 2020/5/19 14:11
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(OperationLogProperties.class)
@ConditionalOnProperty(value = "tellus.operation.enabled", havingValue = "true")
public class OperationLogAutoConfiguration {

    private OperationLogService operationLogService;
    private OperationLogProperties operationLogProperties;

    @Bean
    OperationLogAspect operationLogAspect() {
        OperationLogAspect operationLogAspect = new OperationLogAspect(operationLogProperties);
        operationLogAspect.setOperationLogService(operationLogService);
        return operationLogAspect;
    }

    @Autowired(required = false)
    public void setOperationLogService(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Autowired
    public void setOperationLogProperties(OperationLogProperties operationLogProperties) {
        this.operationLogProperties = operationLogProperties;
    }
}
