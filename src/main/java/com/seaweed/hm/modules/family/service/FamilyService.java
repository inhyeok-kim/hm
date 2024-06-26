package com.seaweed.hm.modules.family.service;


import com.seaweed.hm.modules.family.entity.Family;
import com.seaweed.hm.modules.family.entity.FamilyJoinReq;
import com.seaweed.hm.modules.family.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.repository.FamilyJoinReqRepository;
import com.seaweed.hm.modules.family.repository.FamilyRepository;
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
    public void requestJoin(FamilyJoinReq req) {
        familyJoinReqRepository.save(req);
    }

    public List<FamilyJoinReq> getFamilyJoinReqByUser(long userId, FamilyJoinStatus status) {
        return familyJoinReqRepository.findAllByUserIdAndStatus(userId,status);
    }

    public List<FamilyJoinReq> getFamilyJoinReqByFamily(long familyId, FamilyJoinStatus status) {
        return familyJoinReqRepository.findAllByFamilyIdAndStatus(familyId,status);
    }
}
