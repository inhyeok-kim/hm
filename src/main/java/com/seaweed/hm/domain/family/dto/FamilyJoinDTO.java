package com.seaweed.hm.domain.family.dto;

import com.seaweed.hm.domain.family.entity.FamilyJoin;
import com.seaweed.hm.domain.family.vo.JoinFamilyVO;
import com.seaweed.hm.domain.family.vo.JoinUserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyJoinDTO {
    private long id;

    private JoinUserVO user;
    private JoinFamilyVO family;

    public FamilyJoinDTO(FamilyJoin entity){
        this.id = entity.getId();
    }


}
