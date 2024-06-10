package com.seaweed.hm.modules.user.domain.model.dto;

import com.seaweed.hm.modules.user.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private long familyId;


    public UserDTO(User entity){
        this.id = entity.getUid();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
    }
}
