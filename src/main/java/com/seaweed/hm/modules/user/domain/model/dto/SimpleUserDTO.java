package com.seaweed.hm.modules.user.domain.model.dto;

import com.seaweed.hm.modules.user.domain.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleUserDTO {
    private String id;
    private String name;
    private long familyId;


    public SimpleUserDTO(User entity){
//        this.id = entity.getId();
        this.id = entity.getUid();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
    }
}
