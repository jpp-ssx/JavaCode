package com.example.primary.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyApplicationContext {
    @Bean
    public ServletRegistrationBean getServletBean(){
        return new ServletRegistrationBean(new MyServlet());
    }
    @Bean
    public FilterRegistrationBean getFilterBean(){
        return new FilterRegistrationBean(new MyFilter());
    }


}
