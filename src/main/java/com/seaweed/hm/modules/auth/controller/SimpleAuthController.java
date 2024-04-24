package com.seaweed.hm.modules.auth.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.component.http.session.SessionAuthenticationContext;
import com.seaweed.hm.modules.auth.model.SimpleAuth;
import com.seaweed.hm.modules.auth.usecase.SimpleAuthUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class SimpleAuthController extends DefaultController {

    @Autowired
    private SimpleAuthUsecase simpleAuthUsecase;

    @PostMapping("")
    public ResponseEntity login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Map body
    ) throws Exception {
        String loginId = (String) body.get("loginId");
        String password = (String) body.get("password");
        if(!StringUtils.hasText((loginId)) || !StringUtils.hasText(password)){
            return responseBuilder.responseBadError();
        }

        SimpleAuth simpleAuth = simpleAuthUsecase.login(loginId, password);

        if(simpleAuth == null){
            return responseBuilder.responseFail("no matched id and password");
        } else {
            try {
                SessionAuthenticationContext.authenticate(request.getSession(),simpleAuth.getUserId(),simpleAuth.getType());
            } catch (Exception e){
                return responseBuilder.responseFail();
            }
        }

        return responseBuilder.response();
    }


    @GetMapping("/check")
    public ResponseEntity check(
            HttpServletRequest request,
            HttpServletResponse response
    ){

        if(SessionAuthenticationContext.isAuthenticated(request.getSession())){
            return responseBuilder.response();
        } else {
            return responseBuilder.responseUnAuthenticated();
        }
    }

}
