package com.seaweed.hm.modules.auth.controller;

import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.comm.component.http.session.SessionAuthenticationContext;
import com.seaweed.hm.modules.auth.dto.SimpleAuthDTO;
import com.seaweed.hm.modules.auth.entity.SimpleAuth;
import com.seaweed.hm.modules.auth.usecase.SimpleAuthUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class SimpleAuthController{

    @Autowired
    private SimpleAuthUsecase simpleAuthUsecase;

    @PostMapping("")
    public APIResponse login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Map body
    ) throws Exception {
        String loginId = (String) body.get("loginId");
        String password = (String) body.get("password");
        if(!StringUtils.hasText((loginId)) || !StringUtils.hasText(password)){
            return APIResponse.builder().build();
        }

        SimpleAuthDTO simpleAuth = simpleAuthUsecase.login(loginId, password);

        if(simpleAuth == null){
            return APIResponse.builder().code(-1).message("no matched id and password").build();
        } else {
            SessionAuthenticationContext.authenticate(request.getSession(),simpleAuth.getUserId());
        }

        return APIResponse.builder().build();
    }


    @GetMapping("/check")
    public APIResponse check(
            HttpServletRequest request,
            HttpServletResponse response
    ){

        if(SessionAuthenticationContext.isAuthenticated(request.getSession())){
            return APIResponse.builder().build();
        } else {
            return APIResponse.builder().code(APIResponse.Code.UN_AUTHENTICATED).build();
        }
    }

}
