package com.seaweed.hm.application.family.controller;

import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import com.seaweed.hm.abstracts.controller.DefaultController;
import com.seaweed.hm.comm.http.argument.LoginId;
import com.seaweed.hm.comm.http.response.APIResponse;
import com.seaweed.hm.application.family.query.FamilyJoinQueryService;
import com.seaweed.hm.application.family.command.FamilyJoinService;
import com.seaweed.hm.domain.family.dto.FamilyJoinDTO;
import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.application.family.exception.FamilyContainsUserException;
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
    private FamilyJoinService familyJoinService;

    @Autowired
    private FamilyJoinQueryService familyJoinQueryService;

    @PostMapping("/request")
    public APIResponse join(@LoginId long id, HttpServletRequest req, HttpServletResponse res, @RequestBody Map body) throws NotFoundException {
        String inviteCode = (String) body.get("inviteCode");
        try {
            familyJoinService.joinRequest(id, inviteCode);
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
    ) throws NotFoundException {
        List<FamilyJoinDTO> waitList = familyJoinQueryService.getMyFamilyJoinRequest(id, FamilyJoinStatus.WAIT);
        List<FamilyJoinDTO> approveList = familyJoinQueryService.getMyFamilyJoinRequest(id, FamilyJoinStatus.APPROVE);
        List<FamilyJoinDTO> denyList = familyJoinQueryService.getMyFamilyJoinRequest(id, FamilyJoinStatus.DENY);
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
        List<FamilyJoinDTO> waitList = familyJoinQueryService.getMyJoinRequest(id,FamilyJoinStatus.WAIT);
        List<FamilyJoinDTO> approveList = familyJoinQueryService.getMyJoinRequest(id, FamilyJoinStatus.APPROVE);
        List<FamilyJoinDTO> denyList = familyJoinQueryService.getMyJoinRequest(id, FamilyJoinStatus.DENY);
        Map map = new HashMap<>();
        map.put("waitList",waitList);
        map.put("approveList",approveList);
        map.put("denyList",denyList);
        return APIResponse.builder().data(map).build();
    }
}
