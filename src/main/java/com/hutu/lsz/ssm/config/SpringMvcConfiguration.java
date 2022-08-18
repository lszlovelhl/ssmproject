package com.hutu.lsz.ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 此配置类替代了springmvc.xml，用来扫描Controller类
 */

@Configuration
@ComponentScan(value = "com.hutu.lsz.ssm.controller", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}, useDefaultFilters = false)
public class SpringMvcConfiguration {

}
