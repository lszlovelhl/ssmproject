package com.hutu.lsz.ssm.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.mvc.Controller;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 替代了applicationContext.xml,用于扫描Controller以外的类
 */

// 配置Spring容器
@Configuration
@ComponentScan(value = "com.hutu.lsz.ssm", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
// 开启AOP
@EnableAspectJAutoProxy
// 开启事务支持
@EnableTransactionManagement
// 引入数据源配置
@Import({DataSourceConfiguration.class})

public class SpringConfiguration {
    // MyBatis集成
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.hutu.lsz.ssm.entity, com.hutu.lsz.ssm.vo");
        // 设置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(pageInterceptor);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() throws Exception{
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // 设置SessionFactory名字
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        //设置扫描包
        configurer.setBasePackage("com.hutu.lsz.ssm.mapper");
        return configurer;
    }

    // 用于创建事务管理器对象
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
