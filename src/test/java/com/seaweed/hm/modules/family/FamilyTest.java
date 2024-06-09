package com.seaweed.hm.modules.family;

import com.seaweed.hm.modules.family.domain.dto.FamilyDTO;
import com.seaweed.hm.modules.family.domain.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.domain.dto.FamilyJoinUserDTO;
import com.seaweed.hm.modules.family.domain.entity.Family;
import com.seaweed.hm.modules.family.domain.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.presentation.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.domain.service.FamilyService;
import com.seaweed.hm.modules.family.application.FamilyUsecase;
import com.seaweed.hm.modules.user.entity.User;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class FamilyTest {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyUsecase familyUsecase;

    @Autowired
    private SimpleUserService simpleUserService;

    @Test
    void test(){
        List<Family> list = familyService.getFamilyListAll();
        System.out.println(list.stream().map(FamilyDTO::new).toList());
    }

    @Test
    @Transactional
    void testMyFamily(){
        FamilyDTO familyDTO = familyUsecase.findMyFamily(4);
    }


    @Test
    @Transactional
    void testFamilyUsers(){
        List<Family> list = familyService.getFamilyListAll();
        System.out.println(list.stream().map(FamilyJoinUserDTO::new).toList());
    }

    @Test
    void testCreate(){
        try {
            FamilyDTO familyDTO = familyUsecase.createFamily(4,"새로운팸");
            User user = simpleUserService.getUserById(4);
            Family family = familyService.getFamily(familyDTO.getId());
            Assertions.assertNotNull(family);
            Assertions.assertEquals(user.getFamilyId(),family.getId());
        } catch (UserHasFamilyException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Test
    void testRefrechIvt(){
        try {
            FamilyDTO family = familyUsecase.findMyFamily(1);
            String inviteCode = familyUsecase.refreshInviteCode(family.getId());
            FamilyDTO refreshedFamily = familyUsecase.findMyFamily(1);
            System.out.println(refreshedFamily.getInviteCode());
            Assertions.assertNotEquals(family.getInviteCode(), refreshedFamily.getInviteCode());
            Assertions.assertEquals(inviteCode, refreshedFamily.getInviteCode());

        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testJoinRequest(){
        List<FamilyJoinReqDTO> list = familyUsecase.getMyJoinRequest(4, FamilyJoinStatus.WAIT);
        System.out.println(list);
    }

    @Test
    void testFamilyJoinRequest(){
        List<FamilyJoinReqDTO> list = familyUsecase.getMyFamilyJoinRequest(1, FamilyJoinStatus.WAIT);
        System.out.println(list);
    }
}
