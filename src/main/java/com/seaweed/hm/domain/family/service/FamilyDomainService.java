package com.seaweed.hm.domain.family.service;


import com.seaweed.hm.domain.family.repository.FamilyJoinRepository;
import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.domain.family.repository.FamilyRepository;
import com.seaweed.hm.domain.family.entity.Family;
import com.seaweed.hm.domain.family.entity.FamilyJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyDomainService {

    @Autowired
    private FamilyRepository familyRepository;


    @Autowired
    private FamilyJoinRepository familyJoinRepository;

    public Family getFamily(long id){
        Optional<Family> entity = familyRepository.findById(id);
        if(entity.isPresent()){
            return entity.get();
        } else {
            return null;
        }
    }

    public Family getFamilyByInviteCode(String inviteCode){
        Optional<Family> entity = familyRepository.findByInviteCode(inviteCode);
        return entity.orElse(null);
    }

    @Transactional
    public Family registFamily(Family newFamily){
        return familyRepository.save(newFamily);
    }

    @Transactional
    public void updateInviteCode(Family family) {
        familyRepository.updateInviteCode(family.getId(), family.getInviteCode());
    }

    public List<FamilyJoin> getFamilyJoinReqByUser(long userId, FamilyJoinStatus status) {
        return familyJoinRepository.findAllByUserIdAndStatus(userId,status);
    }

    public List<FamilyJoin> getFamilyJoinReqByFamily(long familyId, FamilyJoinStatus status) {
        return familyJoinRepository.findAllByFamilyIdAndStatus(familyId,status);
    }
}
