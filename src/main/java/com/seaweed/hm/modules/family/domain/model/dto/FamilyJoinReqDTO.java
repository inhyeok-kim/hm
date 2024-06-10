package com.seaweed.hm.modules.family.domain.model.dto;

import com.seaweed.hm.modules.family.domain.model.entity.FamilyJoin;
import com.seaweed.hm.modules.family.domain.model.vo.JoinFamilyVO;
import com.seaweed.hm.modules.family.domain.model.vo.JoinUserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyJoinReqDTO {
    private long id;

    private JoinUserVO user;
    private JoinFamilyVO family;

    public FamilyJoinReqDTO(FamilyJoin entity){
        this.id = entity.getId();
    }


}
