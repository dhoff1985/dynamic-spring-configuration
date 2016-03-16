package com.dthoffman.dynamicspringconfig

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

/**
 * Created by dhoffman on 3/15/16.
 */
@Configuration
@ComponentScan('com.dthoffman.dynamicspringconfig')
@PropertySource(value = 'classpath:/testconfig.properties', ignoreResourceNotFound = false)
class TestSpringConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
