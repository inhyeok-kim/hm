package com.seaweed.hm.common.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;

public abstract class CustomFilter implements Filter {
    private String[] excludeUrl = {};
    public void setExcludeUrl(String[] excludeUrl){
        this.excludeUrl = excludeUrl;
    }

    public abstract void filter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException;

    private boolean isExcluded(String path){
        return Arrays.stream(excludeUrl).anyMatch(path::startsWith);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String path = request.getRequestURI();
        if(!isExcluded(path)){
            filter(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

}
