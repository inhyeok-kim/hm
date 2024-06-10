package com.seaweed.hm.modules.family;

import com.seaweed.hm.modules.family.application.FamilyJoinService;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyDTO;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinDTO;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.domain.model.entity.Family;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.repository.FamilyRepository;
import com.seaweed.hm.modules.family.presentation.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.domain.service.FamilyService;
import com.seaweed.hm.modules.family.application.FamilyUsecase;
import com.seaweed.hm.modules.user.domain.model.entity.User;
import com.seaweed.hm.modules.user.domain.service.SimpleUserService;
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
import java.util.Optional;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class FamilyTest {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyUsecase familyUsecase;

    @Autowired
    private FamilyJoinService familyJoinService;

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
        List<FamilyJoinDTO> list = familyJoinService.getMyJoinRequest(4, FamilyJoinStatus.WAIT);
        System.out.println(list);
    }

    @Test
    void testFamilyJoinRequest(){
        List<FamilyJoinReqDTO> list = familyUsecase.getMyFamilyJoinRequest(1, FamilyJoinStatus.WAIT);
        System.out.println(list);
    }

    @Test
    void testFamily(){
        Optional<Family> familyOpt = familyRepository.findById(1L);
        if(familyOpt.isPresent()){
            System.out.println(familyOpt.get());
        }
    }
}
