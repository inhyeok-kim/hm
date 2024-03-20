package com.seaweed.hm.modules.user.controller;

import com.seaweed.hm.common.controller.DefaultController;
import com.seaweed.hm.modules.user.model.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends DefaultController {

    @GetMapping("")
    public ResponseEntity getMe(
            HttpServletRequest request,
            HttpServletResponse response
    )
    {
        UserDTO loginUser = getLoginUser();
        return buildResponse(loginUser);
    }

}
