package com.seaweed.hm.modules.family.service;


import com.seaweed.hm.modules.family.entity.Family;
import com.seaweed.hm.modules.family.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

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
}
