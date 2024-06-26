package com.seaweed.hm.modules.family.dto;

import com.seaweed.hm.modules.family.entity.Family;
import com.seaweed.hm.modules.user.dto.SimpleUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FamilyDTO {
    private long id;
    private String name;
    private String inviteCode;

    public FamilyDTO(Family entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.inviteCode = entity.getInviteCode();
    }


}
