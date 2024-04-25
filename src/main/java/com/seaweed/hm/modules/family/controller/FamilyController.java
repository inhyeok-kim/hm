package com.seaweed.hm.modules.family.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.modules.family.exception.FamilyContainsUserException;
import com.seaweed.hm.modules.family.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.usecase.FamilyUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/family")
@Slf4j
public class FamilyController extends DefaultController {

    @Autowired
    private FamilyUsecase familyUsecase;

    @PostMapping("/join")
    public APIResponse join(@LoginId long id, HttpServletRequest req, HttpServletResponse res, @RequestBody Map body){
        long familyId = (long) body.get("familyId");
        try {
            familyUsecase.joinRequest(id, familyId);
        } catch (FamilyContainsUserException e) {
            return APIResponse.builder()
                    .code(-1)
                    .message("이미 가입되어 있는 가족입니다.")
                    .build();
        } catch (UserHasFamilyException e) {
            return APIResponse.builder()
                    .code(-2)
                    .message("이미 소속된 가족이 존재합니다.")
                    .build();
        }
        return APIResponse.builder().build();
    }
}
