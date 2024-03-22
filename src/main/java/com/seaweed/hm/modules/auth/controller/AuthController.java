package com.seaweed.hm.modules.auth.controller;

import com.seaweed.hm.common.controller.DefaultController;
import com.seaweed.hm.modules.auth.model.AuthEntity;
import com.seaweed.hm.modules.auth.model.AuthRegistDTO;
import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.user.model.UserDTO;
import com.seaweed.hm.modules.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController extends DefaultController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign")
    public ResponseEntity signOn(HttpServletRequest request, HttpServletResponse response,
        @RequestBody Map body
    ) throws NoSuchAlgorithmException {
        String loginId = (String) body.get("loginId");
        String name = (String) body.get("name");
        String password = (String) body.get("password");
        AuthRegistDTO authRegistDTO = new AuthRegistDTO(loginId, name, authService.encryptPassword(password));

        UserDTO newUser = authService.registUser(authRegistDTO);
        if(newUser == null){
            return buildFailResponse();
        }

        return buildResponse();
    }

    @PostMapping("")
    public ResponseEntity login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Map body
    ) throws NoSuchAlgorithmException {

        String loginId = (String) body.get("loginId");
        String password = (String) body.get("password");

        UserDTO loginUser = authService.login(loginId,authService.encryptPassword(password));
        if(loginUser == null){
            return buildFailResponse("no matched id and password");
        } else {
            request.getSession().setAttribute(AuthService.SESSION_LOGIN_NAME,loginUser);
        }

        return buildResponse();
    }
}
