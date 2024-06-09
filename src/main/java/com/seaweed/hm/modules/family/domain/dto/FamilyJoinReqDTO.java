package com.seaweed.hm.modules.family.domain.dto;

import com.seaweed.hm.modules.family.domain.entity.FamilyJoinReq;
import com.seaweed.hm.modules.user.dto.SimpleUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FamilyJoinReqDTO {
    private long id;
    private long familyId;
    private long userId;

    private SimpleUserDTO user;
    private FamilyDTO family;

    public FamilyJoinReqDTO(FamilyJoinReq entity){
        this.id = entity.getId();
        this.familyId = entity.getFamilyId();
        this.userId = entity.getUserId();
        this.user = new SimpleUserDTO(entity.getUser());
        this.family = new FamilyDTO(entity.getFamily());
    }


}
