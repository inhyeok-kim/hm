package com.seaweed.hm.application.sign.controller;

import com.seaweed.hm.application.sign.command.SignService;
import com.seaweed.hm.application.user.exception.DuplicateUserUidException;
import com.seaweed.hm.application.user.query.UserQueryService;
import com.seaweed.hm.comm.http.response.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignService signService;

    @PostMapping("")
    public APIResponse signOn(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody Map body
    ) throws Exception {
        String loginId = (String) body.get("loginId");
        String name = (String) body.get("name");
        String password = (String) body.get("password");

        try {
            signService.sign(loginId, password, name);
        } catch (DuplicateUserUidException de){
            return APIResponse.builder().code(-1).message("중복된 아이디").build();
        }
        return APIResponse.builder().build();
    }
}
