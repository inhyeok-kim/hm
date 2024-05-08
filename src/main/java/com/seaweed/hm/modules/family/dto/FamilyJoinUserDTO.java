package com.seaweed.hm.modules.family.dto;

import com.seaweed.hm.modules.family.entity.Family;
import com.seaweed.hm.modules.user.dto.SimpleUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FamilyJoinUserDTO {
    private long id;
    private String name;
    private String inviteCode;
    private List<SimpleUserDTO> users;

    public FamilyJoinUserDTO(Family entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.inviteCode = entity.getInviteCode();
        this.users = entity.getUsers().stream().map(SimpleUserDTO::new).toList();
    }


}
