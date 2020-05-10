package com.master4.config;

import com.master4.filter.AdminFilter;
import com.master4.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

//@Configuration
public class FilterConfig {
/*
    private final AdminFilter adminFilter;

    @Autowired
    public FilterConfig(AdminFilter adminFilter){
        this.adminFilter = adminFilter;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> AdminFilterRegistration() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
        return registrationBean;
    }

    /*@Bean
    @Order(0)
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/**");
        registrationBean.setOrder(0);
        return registrationBean;
    }*/

}
