package com.seaweed.hm.application.family.controller;

import com.seaweed.hm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.http.argument.LoginId;
import com.seaweed.hm.comm.http.response.APIResponse;
import com.seaweed.hm.application.family.query.FamilyQueryService;
import com.seaweed.hm.domain.family.dto.FamilyDTO;
import com.seaweed.hm.application.family.command.FamilyService;
import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/family")
@Slf4j
public class FamilyController extends DefaultController {

    @Autowired
    private FamilyService familyService;
    @Autowired
    private FamilyQueryService familyQueryService;

    @GetMapping("/my")
    public APIResponse getMyFamily(
            @LoginId long id, HttpServletRequest req, HttpServletResponse res
    ) throws NotFoundException {
        FamilyDTO myFamily = familyQueryService.findMyFamily(id);
        if(myFamily==null){
            return APIResponse.builder()
                    .message("가입된 가족이 존재하지 않습니다.")
                    .code(-1)
                    .build();
        }

        return APIResponse.builder().data(myFamily).build();
    }

    @PostMapping("/create")
    public APIResponse create(@LoginId long id, HttpServletRequest req, HttpServletResponse res, @RequestBody Map body) throws NotFoundException {
        String name = (String) body.get("name");
        FamilyDTO result;
        try {
             result = familyService.createFamily(id,name);
        } catch (UserHasFamilyException e) {
            return APIResponse.builder()
                    .message(e.getMessage())
                    .code(-1)
                    .build();
        }

        return APIResponse.builder().data(result).build();
    }

    @GetMapping("/refresh-ivt")
    public APIResponse refreshIvt(
            @LoginId long id, HttpServletRequest req, HttpServletResponse res
    ) throws NotFoundException {
        String inviteCode = familyService.refreshInviteCode(id);
        return APIResponse.builder().data(inviteCode).build();
    }

}
