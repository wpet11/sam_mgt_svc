package com.csf.basedata.sammgt.domain.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author allen.jin
 * @date 2020/4/16
 */

@Slf4j
@ConditionalOnProperty(prefix = "platform.ds", name = "enable", havingValue = "true")
@Configuration
public class SamDataSourcesConfig {



    @Bean("samClientDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource samClientDataSource() {
        log.info("db1初始化");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("samPlatformDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource samPlatformDataSource() {
        log.info("db2初始化");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
