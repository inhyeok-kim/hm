package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.modules.family.model.FamilyDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String uid;
    private String name;
    private FamilyDTO family;

    public UserDTO(UserEntity entity, boolean includeFamily){
        this.id = entity.getId();
        this.uid = entity.getUid();
        this.name = entity.getName();
        if(includeFamily){
            FamilyDTO _family = new FamilyDTO(entity.getFamily(),false);
            this.family = _family;
        }
    }

    public UserDTO(UserEntity entity){
        this(entity,false);
    }

    public UserEntity toEntity(){
        UserEntity entity = new UserEntity();
        entity.setName(this.name);
        entity.setUid(this.uid);
        if(this.family !=null){
            entity.setFamily(this.family.toEntity());
        }
        return entity;
    }

}
