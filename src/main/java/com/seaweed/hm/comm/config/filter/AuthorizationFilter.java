package com.seaweed.hm.comm.config.filter;

import com.seaweed.hm.comm.abstracts.filter.SimpleFilter;
import com.seaweed.hm.modules.auth.model.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthorizationFilter extends SimpleFilter {

    private Role role;

    public AuthorizationFilter(Role role){
        this.role = role;
    }

    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
