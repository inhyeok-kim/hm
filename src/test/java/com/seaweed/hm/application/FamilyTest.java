package com.seaweed.hm.application;

import com.seaweed.hm.application.family.query.FamilyJoinQueryService;
import com.seaweed.hm.application.family.command.FamilyJoinService;
import com.seaweed.hm.application.family.query.FamilyQueryService;
import com.seaweed.hm.domain.family.dto.FamilyDTO;
import com.seaweed.hm.domain.family.dto.FamilyJoinDTO;
import com.seaweed.hm.domain.family.entity.Family;
import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.domain.family.repository.FamilyRepository;
import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import com.seaweed.hm.application.family.command.FamilyService;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
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
    private FamilyQueryService familyQueryService;
    @Autowired
    private FamilyJoinQueryService familyJoinQueryService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyJoinService familyJoinService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testMyFamily() throws NotFoundException {
        FamilyDTO familyDTO = familyQueryService.findMyFamily(3);
        System.out.println(familyDTO);
        Assertions.assertNotNull(familyDTO);
    }

    @Test
    void testCreate() throws UserHasFamilyException, NotFoundException {
        FamilyDTO familyDTO = familyService.createFamily(4,"새로운팸");
        User user = userRepository.findById(4L).get();
        Family family = familyRepository.findById(familyDTO.getId()).get();
        Assertions.assertNotNull(family);
        Assertions.assertEquals(user.getFamilyId(),family.getId());
    }

    @Transactional
    @Test
    void testRefrechIvt(){
        try {
            FamilyDTO family = familyQueryService.findMyFamily(1);
            String inviteCode = familyService.refreshInviteCode(family.getId());
            FamilyDTO refreshedFamily = familyQueryService.findMyFamily(1);
            System.out.println(refreshedFamily.getInviteCode());
            Assertions.assertNotEquals(family.getInviteCode(), refreshedFamily.getInviteCode());
            Assertions.assertEquals(inviteCode, refreshedFamily.getInviteCode());

        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testJoinRequest(){
        List<FamilyJoinDTO> list = familyJoinQueryService.getMyJoinRequest(4, FamilyJoinStatus.WAIT);
        System.out.println(list);
    }

    @Test
    void testFamilyJoinRequest() throws NotFoundException {
        List<FamilyJoinDTO> list = familyJoinQueryService.getMyFamilyJoinRequest(1, FamilyJoinStatus.WAIT);
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
