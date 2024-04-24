package com.seaweed.hm.comm.config.filter;

import com.seaweed.hm.comm.abstracts.filter.SimpleFilter;
import com.seaweed.hm.comm.component.http.session.SessionAuthenticationContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends SimpleFilter {

    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if(SessionAuthenticationContext.isAuthenticated(session)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\" : -100, \"message\" : \"unAuthenticated\"}");
        }

    }
}
