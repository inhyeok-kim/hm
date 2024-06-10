package com.seaweed.hm.modules.family.domain.service;


import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.repository.FamilyRepository;
import com.seaweed.hm.modules.family.domain.model.entity.Family;
import com.seaweed.hm.modules.family.domain.model.entity.FamilyJoin;
import com.seaweed.hm.modules.family.domain.repository.FamilyJoinReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;


    @Autowired
    private FamilyJoinReqRepository familyJoinReqRepository;

    public List<Family> getFamilyListAll(){
        return familyRepository.findFamilyJoinUser();
    }

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

    @Transactional
    public void requestJoin(FamilyJoin req) {
        familyJoinReqRepository.save(req);
    }

    public List<FamilyJoin> getFamilyJoinReqByUser(long userId, FamilyJoinStatus status) {
        return familyJoinReqRepository.findAllByUserIdAndStatus(userId,status);
    }

    public List<FamilyJoin> getFamilyJoinReqByFamily(long familyId, FamilyJoinStatus status) {
        return familyJoinReqRepository.findAllByFamilyIdAndStatus(familyId,status);
    }
}
