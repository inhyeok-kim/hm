package com.seaweed.hm.modules.family.application;

import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinDTO;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.repository.FamilyJoinQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyJoinService {
    @Autowired
    private FamilyJoinQueryRepository familyJoinQueryRepository;

    public List<FamilyJoinDTO> getMyJoinRequest(long loginId, FamilyJoinStatus status){
        return familyJoinQueryRepository.findAllByUserIdAndStatus(loginId, status);
    }

}
