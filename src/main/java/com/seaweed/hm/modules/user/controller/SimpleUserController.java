package com.seaweed.hm.modules.user.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.modules.user.dto.SimpleUserDTO;
import com.seaweed.hm.modules.user.usecase.SimpleUserUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SimpleUserController extends DefaultController {

    @Autowired
    private SimpleUserUsecase simpleUserUsecase;

    @GetMapping("/me")
    public APIResponse getMe(
            @LoginId long loginId,
            HttpServletRequest request,
            HttpServletResponse response
    )
    {
        SimpleUserDTO user = simpleUserUsecase.findUserById(loginId);
        if(user == null) {
            return APIResponse.builder()
                    .code(-1)
                    .message("사용자 정보를 찾을 수 없습니다.")
                    .build();
        }
        return APIResponse.builder().data(user).build();
    }

}
