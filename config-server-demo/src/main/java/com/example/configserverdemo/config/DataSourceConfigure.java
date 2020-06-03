package com.example.configserverdemo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RefreshScope
@Configuration
public class DataSourceConfigure {

    @Bean
    @RefreshScope// 刷新配置文件  @RefreshScope要与@Configuration匹配
    @ConfigurationProperties(prefix="spring.datasource") // 数据源的自动配置的前缀
    public javax.sql.DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
}
