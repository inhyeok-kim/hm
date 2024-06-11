package com.seaweed.hm.application.family.command;


import com.seaweed.hm.domain.family.dto.FamilyDTO;
import com.seaweed.hm.domain.family.entity.Family;
import com.seaweed.hm.domain.family.repository.FamilyQueryRepository;
import com.seaweed.hm.domain.family.repository.FamilyRepository;
import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import com.seaweed.hm.domain.family.service.FamilyDomainService;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.domain.user.service.UserDomainService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyDomainService familyDomainService;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private FamilyQueryRepository familyQueryRepository;


    /**
     * 새로운 가족을 만드는 usecase
     * @param loginId
     * @param name
     * @return
     * @throws UserHasFamilyException 이미 가입된 가족이 존재하는 경우
     */
    @Transactional
    public FamilyDTO createFamily(long loginId, String name) throws UserHasFamilyException, NotFoundException {
        Optional<User> userOpt = userRepository.findById(loginId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");

        User user = userOpt.get();
        Family newFamily = Family.builder().name(name).creator(user).build();
        newFamily = familyRepository.save(newFamily);
        userDomainService.joinFamily(user,newFamily);

        return familyQueryRepository.findById(newFamily.getId());
    }

    /**
     * 가족 초대코드를 갱신하는 usecase
     * @param id
     * @return
     * @throws NotFoundException 소속된 가족이 존재하지 않는 경우
     */
    @Transactional
    public String refreshInviteCode(long id) throws NotFoundException {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");
        User user = userOpt.get();

        Optional<Family> familyOpt = familyRepository.findById(user.getFamilyId());
        if(familyOpt.isEmpty()) throw new NotFoundException("소속된 가족이 존재하지 않습니다.");
        Family family = familyOpt.get();

        return family.refreshInviteCode();
    }
}
