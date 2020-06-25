package com.tellus.web;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Tellus web starter 配置
 *
 * @author Roy
 * @date 2020/6/23 13:36
 */
@Data
@Configuration
@ComponentScan(basePackages = {"com.tellus"})
@EnableConfigurationProperties(TellusWebProperties.class)
public class TellusWebConfiguration {
}
