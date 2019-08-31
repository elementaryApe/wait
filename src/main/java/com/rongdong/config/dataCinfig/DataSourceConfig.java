package com.rongdong.config.dataCinfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 数据源配置类
 *
 * @author hsh
 * @create 2018-03-21 19:06
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "threeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.three") // application.properteis中对应属性的前缀
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "fourDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.four") // application.properteis中对应属性的前缀
    public DataSource dataSource4() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "fiveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.five") // application.properteis中对应属性的前缀
    public DataSource dataSource5() {
        return DataSourceBuilder.create().build();
    }
}
