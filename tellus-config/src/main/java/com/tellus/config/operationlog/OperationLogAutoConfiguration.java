package com.tellus.config.operationlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

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

    @Autowired(required = false)
    private OperationLogService operationLogService;

    @Autowired
    private OperationLogProperties operationLogProperties;


}
