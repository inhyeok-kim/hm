package com.seaweed.hm.common.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter filter = new AuthFilter();
        filter.setExcludeUrl(new String[]{"/auth","/h2-console"});
        registrationBean.setFilter(filter);
        registrationBean.setOrder(1);
        registrationBean.setName("Auth filter");
        registrationBean.addUrlPatterns("*");
        return registrationBean;
    }
}
