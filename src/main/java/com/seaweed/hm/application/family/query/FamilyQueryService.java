package com.seaweed.hm.application.family.query;


import com.seaweed.hm.domain.family.dto.FamilyDTO;
import com.seaweed.hm.domain.family.repository.FamilyQueryRepository;
import com.seaweed.hm.domain.family.service.FamilyDomainService;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.domain.user.service.UserDomainService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyQueryService {

    @Autowired
    private FamilyDomainService familyDomainService;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyQueryRepository familyQueryRepository;



    /**
     * 사용자의 가족 정보를 가져오는 usecase
     * @param loginId
     * @return
     */
    public FamilyDTO findMyFamily(long loginId) throws NotFoundException {
        Optional<User> userOpt = userRepository.findById(loginId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자.");
        return  familyQueryRepository.findById(userOpt.get().getFamilyId());
    }

}
