package com.seaweed.hm.modules.family.service;


import com.seaweed.hm.modules.family.entity.FamilyEntity;
import com.seaweed.hm.modules.family.model.Family;
import com.seaweed.hm.modules.family.model.FamilyDTO;
import com.seaweed.hm.modules.family.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    public List<FamilyDTO> getFamilyListAll(){
        return familyRepository.findFamilyJoinUser().stream().map(familyEntity -> new FamilyDTO(familyEntity)).toList();
    }

    public Family getFamily(long id){
        Optional<FamilyEntity> entity = familyRepository.findById(id);
        if(entity.isPresent()){
            return new Family(entity.get());
        } else {
            return null;
        }
    }
}
