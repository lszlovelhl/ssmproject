package com.hutu.lsz.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

//// 配置Spring容器
//@Configuration
//// 加载配置文件
//@PropertySource("classpath:jdbc.properties")
//public class DataSourceConfiguration {
//    @Value("${jdbc.driverClass}")
//    private String driverClass;
//    @Value("${jdbc.url}")
//    private String url;
//    @Value("${jdbc.username}")
//    private String username;
//    @Value("${jdbc.password}")
//    private String password;
//
//    // 数据源
//    @Bean
//    public DataSource dataSource(){
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setDriverClassName(driverClass);
//        druidDataSource.setUrl(url);
//        druidDataSource.setUsername(username);
//        druidDataSource.setPassword(password);
//        return druidDataSource;
//    }
//
//}

// 配置Spring容器
@Configuration
// 加载配置文件
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfiguration {
    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    // 数据源
    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }
}

