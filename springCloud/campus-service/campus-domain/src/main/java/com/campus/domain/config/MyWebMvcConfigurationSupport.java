package com.campus.domain.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    private final static Logger logger= LoggerFactory.getLogger(MyWebMvcConfigurationSupport.class);

    @Resource
    private MyHandlerInterceptor myHandlerInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myHandlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login","/register");
        super.addInterceptors(registry);
    }
}
