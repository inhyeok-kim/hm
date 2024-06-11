package com.seaweed.hm.application.family.query;

import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.domain.family.dto.FamilyJoinDTO;
import com.seaweed.hm.domain.family.repository.FamilyJoinQueryRepository;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyJoinQueryService {
    @Autowired
    private FamilyJoinQueryRepository familyJoinQueryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FamilyJoinDTO> getMyJoinRequest(long loginId, FamilyJoinStatus status){
        return familyJoinQueryRepository.findAllByUserAndStatus(loginId, status);
    }

    /**
     * 특정 가족의 가입 요청 목록을 가져오는 usecase
     * @param loginId
     * @param status
     * @return
     */
    public List<FamilyJoinDTO> getMyFamilyJoinRequest(long loginId, FamilyJoinStatus status) throws NotFoundException {
        Optional<User> userOpt = userRepository.findById(loginId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");
        return familyJoinQueryRepository.findAllByFamilyAndStatus(userOpt.get().getFamilyId(), status);
    }

}
