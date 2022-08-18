package com.hutu.lsz.ssm.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * 定制SpringMVC内部组件
 */
@Configuration
// 开启SpringMVC定制配置功能
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {
    // Servlet3多部分数据解析工具
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    // 方法参数或返回值的校验处理
    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    // 配置日期格式
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 配置java.util.Date
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setPattern("yyyy-MM-dd HH:mm:ss");
        registry.addFormatter(dateFormatter);

        // JDK8的新日期API
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm:ss"));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(registry);
    }

    // 不再需要，RESTful风格中没有页面
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    // 配置数据校验
    @Override
    public Validator getValidator() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 读取配置文件的编码格式
        messageSource.setDefaultEncoding("utf-8");
        // 缓存时间，-1表示不过期
        messageSource.setCacheMillis(-1);
        // 配置文件前缀名，设置为Messages,那你的配置文件必须以Messages.properties/Message_en.properties...
        messageSource.setBasename("classpath:ValidatedMessages");

        // 校验器工厂对象
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        // 可以不设置，默认会自动查找校验实现类
        factoryBean.setProviderClass(HibernateValidator.class);
        // 消息源
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }

    // 不再需要，RESTful风格中没有页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    // 添加异常处理器
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        // resolvers.add(new ApplicationExceptionResolver());
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns配置拦截的请求，excludePathPatterns配置放行的请求
        // registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/product/*");
        // 所有的请求都进入拦截器
        // registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    }
}