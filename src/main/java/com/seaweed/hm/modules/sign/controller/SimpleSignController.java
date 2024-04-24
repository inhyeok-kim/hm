package com.seaweed.hm.modules.sign.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.modules.sign.usecase.SimpleSignUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sign")
public class SimpleSignController extends DefaultController {

    @Autowired
    private SimpleSignUsecase simpleSignUsecase;

    @PostMapping("")
    public ResponseEntity signOn(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody Map body
    ) throws Exception {
        String loginId = (String) body.get("loginId");
        String name = (String) body.get("name");
        String password = (String) body.get("password");

        simpleSignUsecase.sign(loginId, password, name);
        return responseBuilder.response();
    }

}
