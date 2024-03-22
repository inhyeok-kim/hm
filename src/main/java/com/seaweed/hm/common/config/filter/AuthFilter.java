package com.seaweed.hm.common.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.user.model.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class AuthFilter extends CustomFilter {
    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute(AuthService.SESSION_LOGIN_NAME);
        if(loginUser == null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\" : -100, \"message\" : \"unAuthenticated\"}");
        }

    }
}
