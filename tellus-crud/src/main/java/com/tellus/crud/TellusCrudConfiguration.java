package com.tellus.crud;

import com.tellus.toolkit.SensitiveCryptKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CRUD 启动配置
 *
 * @author Roy
 * @date 2020/5/23 12:23
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(TellusCrudProperties.class)
public class TellusCrudConfiguration {

    private final TellusCrudProperties crudProperties;

    @Autowired
    public TellusCrudConfiguration(TellusCrudProperties crudProperties) {
        this.crudProperties = crudProperties;
    }

    @Bean
    @ConditionalOnMissingBean(SensitiveCryptKit.class)
    public SensitiveCryptKit sensitiveCryptKit() {
        return new SensitiveCryptKit(crudProperties.getSensitiveSecret(),
                crudProperties.getSensitiveFields());
    }

}
