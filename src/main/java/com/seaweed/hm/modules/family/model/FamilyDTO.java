package com.seaweed.hm.modules.family.model;

import com.seaweed.hm.modules.family.entity.FamilyEntity;
import com.seaweed.hm.modules.user.model.SimpleUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FamilyDTO {
    private long id;
    private String name;
    private List<SimpleUserDTO> users;

    public FamilyDTO(FamilyEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.users = entity.getUsers().stream().map(userEntity -> new SimpleUserDTO(userEntity)).toList();
    }

}
