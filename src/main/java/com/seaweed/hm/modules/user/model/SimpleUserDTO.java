package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.modules.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleUserDTO {
    private long id;
    private String uid;
    private String name;
    private long familyId;

    public SimpleUserDTO(SimpleUser user){
        this.id = user.getId();
        this.uid = user.getUid();
        this.name = user.getName();
    }

    public SimpleUserDTO(UserEntity entity){
        this.id = entity.getId();
        this.uid = entity.getUid();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
    }
}
