package com.tellus.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Tellus web starter 配置
 *
 * @author Roy
 * @date 2020/6/23 13:36
 */
@Data
@ConfigurationProperties(prefix = "tellus.web")
public class TellusWebProperties {
}
