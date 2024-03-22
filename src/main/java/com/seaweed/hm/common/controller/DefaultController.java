package com.seaweed.hm.common.controller;

import com.seaweed.hm.common.response.ResponseBuilder;
import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.user.model.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;

public class DefaultController {

    @Autowired
    public ResponseBuilder responseBuilder;

    public UserDTO getLoginUser(){
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
        UserDTO loginUser = (UserDTO) httpSession.getAttribute(AuthService.SESSION_LOGIN_NAME);

        return loginUser;
    }


}
