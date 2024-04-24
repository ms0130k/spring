package com.playground.spring.config;

import com.playground.spring.config.CachedBodyHttpServletRequest;
import com.playground.spring.config.filter.AuthenticationFilter;
import com.playground.spring.config.filter.ContentCachingFilter;
import com.playground.spring.config.filter.LoggingFilter;
import com.playground.spring.config.filter.ThrowFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        registrationBean.addUrlPatterns("/api/*");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
//        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthenticationFilter());
//        registrationBean.addUrlPatterns("/api/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<ThrowFilter> throwFilter() {
//        FilterRegistrationBean<ThrowFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new ThrowFilter());
//        registrationBean.addUrlPatterns("/api/*");
//        registrationBean.setOrder(3);
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<ContentCachingFilter> contentCachingFilter() {
        FilterRegistrationBean<ContentCachingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ContentCachingFilter());
        registrationBean.addUrlPatterns("/aop/*");
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
