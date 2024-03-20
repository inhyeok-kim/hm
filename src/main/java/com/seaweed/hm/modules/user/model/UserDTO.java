package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.modules.family.model.FamilyDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String loginId;
    private String name;
    private FamilyDTO family;
    private String password;
    public UserDTO(UserEntity entity, boolean includeFamily){
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
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
        entity.setLoginId(this.loginId);
        entity.setPassword(this.password);
        if(this.family !=null){
            entity.setFamily(this.family.toEntity());
        }
        return entity;
    }

}
