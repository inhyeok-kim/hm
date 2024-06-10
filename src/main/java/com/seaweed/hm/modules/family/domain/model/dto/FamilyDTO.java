package com.seaweed.hm.modules.family.domain.model.dto;

import com.seaweed.hm.modules.family.domain.model.entity.Family;
import lombok.Data;
import lombok.NoArgsConstructor;

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
