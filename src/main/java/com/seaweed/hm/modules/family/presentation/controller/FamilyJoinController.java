package com.seaweed.hm.modules.family.presentation.controller;

import com.seaweed.hm.comm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.presentation.exception.FamilyContainsUserException;
import com.seaweed.hm.modules.family.presentation.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.application.FamilyUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/family/join")
@Slf4j
public class FamilyJoinController extends DefaultController {

    @Autowired
    private FamilyUsecase familyUsecase;

    @GetMapping("/refresh-ivt")
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

    @PostMapping("/request")
    public APIResponse join(@LoginId long id, HttpServletRequest req, HttpServletResponse res, @RequestBody Map body){
        String inviteCode = (String) body.get("inviteCode");
        try {
            familyUsecase.joinRequest(id, inviteCode);
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

    @GetMapping("/request/list")
    public APIResponse getFamilyJoinRequest(
            @LoginId long id, HttpServletRequest req, HttpServletResponse res
    ){
        List<FamilyJoinReqDTO> waitList = familyUsecase.getMyFamilyJoinRequest(id, FamilyJoinStatus.WAIT);
        List<FamilyJoinReqDTO> approveList = familyUsecase.getMyFamilyJoinRequest(id, FamilyJoinStatus.APPROVE);
        List<FamilyJoinReqDTO> denyList = familyUsecase.getMyFamilyJoinRequest(id, FamilyJoinStatus.DENY);
        Map map = new HashMap<>();
        map.put("waitList",waitList);
        map.put("approveList",approveList);
        map.put("denyList",denyList);
        return APIResponse.builder().data(map).build();
    }

    @GetMapping("/request/list/my")
    public APIResponse getMyJoinRequest(
            @LoginId long id, HttpServletRequest req, HttpServletResponse res
    ){
        List<FamilyJoinReqDTO> waitList = familyUsecase.getMyJoinRequest(id,FamilyJoinStatus.WAIT);
        List<FamilyJoinReqDTO> approveList = familyUsecase.getMyJoinRequest(id, FamilyJoinStatus.APPROVE);
        List<FamilyJoinReqDTO> denyList = familyUsecase.getMyJoinRequest(id, FamilyJoinStatus.DENY);
        Map map = new HashMap<>();
        map.put("waitList",waitList);
        map.put("approveList",approveList);
        map.put("denyList",denyList);
        return APIResponse.builder().data(map).build();
    }
}
