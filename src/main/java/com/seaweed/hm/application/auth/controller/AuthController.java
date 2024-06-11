package com.seaweed.hm.application.auth.controller;

import com.seaweed.hm.comm.http.argument.LoginId;
import com.seaweed.hm.comm.http.response.APIResponse;
import com.seaweed.hm.comm.http.session.SessionAuthenticationContext;
import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.comm.util.crypto.AESCryptoUtil;
import com.seaweed.hm.application.auth.command.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("remember")
    public APIResponse rememberLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @CookieValue(name = "hmm_remember", required = false) String remember
    ) throws Exception {
        if(!StringUtils.hasText((remember))){
            throw new BadRequestException();
        }

        String cookieValue = AESCryptoUtil.decrypt(remember,"hmmrememberkey12");
        String[] splt = cookieValue.split(":");
        long authId = authService.login(splt[0], splt[1]);

        if(authId == 0){
            return APIResponse.builder().code(-1).message("no matched id and password").build();
        } else {
            SessionAuthenticationContext.authenticate(request.getSession(),authId);
        }

        return APIResponse.builder().build();
    }

    @PostMapping("")
    public APIResponse login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Map body
    ) throws Exception {
        String loginId = (String) body.get("loginId");
        String password = (String) body.get("password");
        boolean remember = (boolean) body.get("remember");
        if(!StringUtils.hasText((loginId)) || !StringUtils.hasText(password)){
            throw new BadRequestException();
        }

        long authId = authService.login(loginId, password);

        if(authId == 0){
            return APIResponse.builder().code(-1).message("no matched id and password").build();
        } else {
            SessionAuthenticationContext.authenticate(request.getSession(),authId);
            if(remember){
                Cookie cookie = new Cookie("hmm_remember", AESCryptoUtil.encrypt(loginId+":"+password,"hmmrememberkey12"));
                cookie.setMaxAge(60*60*24*30);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
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

    @PostMapping("/logout")
    public APIResponse logout(
            HttpServletRequest request,
            HttpServletResponse response,
            @LoginId long loginId
    ) throws UnAuthorizationException {
        if(loginId <= 0){
            throw new UnAuthorizationException("");
        }
        SessionAuthenticationContext.expireAuthentication(request.getSession());

        Cookie cookie = new Cookie("hmm_remember", "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return APIResponse.builder().build();
    }

}
