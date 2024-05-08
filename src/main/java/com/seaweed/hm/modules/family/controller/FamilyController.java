package com.seaweed.hm.modules.family.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.modules.family.dto.FamilyDTO;
import com.seaweed.hm.modules.family.exception.FamilyContainsUserException;
import com.seaweed.hm.modules.family.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.usecase.FamilyUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/family")
@Slf4j
public class FamilyController extends DefaultController {

    @Autowired
    private FamilyUsecase familyUsecase;

    @GetMapping("/my")
    public APIResponse getMyFamily(
            @LoginId long id, HttpServletRequest req, HttpServletResponse res
    ){
        FamilyDTO myFamily = familyUsecase.findMyFamily(id);
        if(myFamily==null){
            return APIResponse.builder()
                    .message("가입된 가족이 존재하지 않습니다.")
                    .code(-1)
                    .build();
        }
        return APIResponse.builder().build();
    }

    @PostMapping("/create")
    public APIResponse create(@LoginId long id, HttpServletRequest req, HttpServletResponse res, @RequestBody Map body){
        String name = (String) body.get("name");

        return APIResponse.builder().build();
    }

    @PostMapping("/refresh-ivt")
    public APIResponse refreshIvt(
            @LoginId long id, HttpServletRequest req, HttpServletResponse res
    ) {
        String inviteCode = "";
        try {
            inviteCode = familyUsecase.refreshInviteCode(id);
        } catch (NotFoundException e) {
            return APIResponse.builder()
                    .message("가입된 가족이 존재하지 않습니다.")
                    .code(-1)
                    .build();
        }
        return APIResponse.builder().data(inviteCode).build();
    }

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
