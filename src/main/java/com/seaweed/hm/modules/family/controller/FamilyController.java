package com.seaweed.hm.modules.family.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.modules.family.dto.FamilyDTO;
import com.seaweed.hm.modules.family.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.enums.FamilyJoinStatus;
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
import java.util.HashMap;
import java.util.List;
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

}
