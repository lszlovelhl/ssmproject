package com.hutu.lsz.ssm.config;

import lombok.val;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.io.File;
import java.util.EnumSet;

// 初始化Spring容器和SpringMVC容器，替代web.xml
// 不是Spring配置类，被支持Servlet3+的web容器在启动时加载
public class WebConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 注册Web组件，此处以字符集过滤器为例
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 首先要触发父类的onStartup，保证先初始化父类中的Web组件
        super.onStartup(servletContext);

        // 创建字符集过滤器对象
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        // 设置字符集
        encodingFilter.setEncoding("UTF-8");
        // 添加到Web容器（不是Spring的Ioc容器，而是ServletContainer）
//         FilterRegistration.Dynamic filterRegistration = new ServletContext().addFilter("characterEncodingFilter", encodingFilter);
        FilterRegistration.Dynamic filterRegistration = registerServletFilter(servletContext, encodingFilter);
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        filterRegistration.addMappingForUrlPatterns(disps, true, "/*");
    }

    // DispatcherServlet初始化完后可更改设置
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        super.customizeRegistration(registration);

        // 配置对multipart的支持
        // 临时文件路径
        String location = "c:/tmp/";
        File file = new File(location);
        if (!file.exists() && !file.isDirectory()){
            file.mkdir();
        }
        // 上传参数
        long maxFileSize = 2097152; // 2M
        long maxRequestSize = 4194304; // 4M
        int fileSizeThreshold = 0;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);

        // 设置404异常处理的参数，即404时让DispatchServlet抛出throwExceptionNoHandlerFound
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    // 创建Spring的Ioc容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // 创建SpringMVC的Ioc容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    // 将DispatcherServlet映射到"/"
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
