package com.seaweed.hm.modules.user.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.modules.user.model.SimpleUserDTO;
import com.seaweed.hm.modules.user.usecase.SimpleUserUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SimpleUserController extends DefaultController {

    @Autowired
    private SimpleUserUsecase simpleUserUsecase;

    @GetMapping("/me")
    public ResponseEntity getMe(
            @LoginId long loginId,
            HttpServletRequest request,
            HttpServletResponse response
    )
    {
        SimpleUserDTO user = simpleUserUsecase.findUserById(loginId);
        if(user == null) {
            return responseBuilder.responseFail("사용자 정보를 찾을 수 없습니다.");
        }
        return responseBuilder.response(user);
    }

}
