package com.seaweed.hm.comm.abstracts.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public abstract class SimpleFilter implements Filter {
    private String[] excludeUrl = {};
    public void setExcludeUrl(String[] excludeUrl){
        this.excludeUrl = excludeUrl;
    }

    public abstract void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    private boolean isExcluded(String path){
        return Arrays.stream(excludeUrl).anyMatch(path::startsWith);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String path = request.getRequestURI();
        if(isExcluded(path)) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            filter(servletRequest, servletResponse, filterChain);
        }
    }

}
