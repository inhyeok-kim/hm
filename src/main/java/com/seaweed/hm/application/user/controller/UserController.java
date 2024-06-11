package com.seaweed.hm.application.user.controller;

import com.seaweed.hm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.http.argument.LoginId;
import com.seaweed.hm.comm.http.response.APIResponse;
import com.seaweed.hm.application.user.query.UserQueryService;
import com.seaweed.hm.application.user.command.UserService;
import com.seaweed.hm.domain.user.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends DefaultController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserQueryService userQueryService;

    @GetMapping("/me")
    public APIResponse getMyInfo(
            @LoginId long loginId,
            HttpServletRequest request,
            HttpServletResponse response
    )
    {
        UserDTO user = userQueryService.findUserById(loginId);
        if(user == null) {
            return APIResponse.builder()
                    .code(-1)
                    .message("사용자 정보를 찾을 수 없습니다.")
                    .build();
        }
        return APIResponse.builder().data(user).build();
    }

}
