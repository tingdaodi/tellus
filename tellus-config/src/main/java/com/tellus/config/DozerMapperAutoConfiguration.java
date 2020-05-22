package com.tellus.config;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import com.tellus.config.dozer.DozerGenerator;
import com.tellus.config.dozer.PageDozerGenerator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Dozer 深度复制
 *
 * @author Roy
 * @date 2020/5/22 13:59
 */
@Configuration
public class DozerMapperAutoConfiguration {

    @Bean
    public DozerGenerator dozerGenerator() {
        return new DozerGenerator();
    }

    @Bean
    @ConditionalOnClass(name = "com.baomidou.mybatisplus.core.metadata.IPage")
    public PageDozerGenerator pageDozerGenerator() {
        return new PageDozerGenerator();
    }

    @Bean
    @SneakyThrows
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozer/*.xml") Resource[] resources) {
        final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }

}
