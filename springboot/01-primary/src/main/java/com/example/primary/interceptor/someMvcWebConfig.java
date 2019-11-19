package com.example.primary.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class someMvcWebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        someInterceptor someInterceptor = new someInterceptor();
        registry.addInterceptor(someInterceptor).addPathPatterns("/some/**").excludePathPatterns("/some/queryTotalCount");
    }
}
