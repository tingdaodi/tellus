package com.tellus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description TODO
 *
 * @author Roy
 * @date 2020/5/19 12:36
 */
@Data
@ConfigurationProperties(prefix = "tellus.config")
public class TellusGeneralProperties {

    private String system = "system";

}
