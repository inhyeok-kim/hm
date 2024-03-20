package com.seaweed.hm.common.service;

import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.user.model.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class DefaultService {
    private UserDTO getLoginUser(){
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
        UserDTO loginUser = (UserDTO) httpSession.getAttribute(AuthService.SESSION_LOGIN_NAME);

        return loginUser;
    }
}
