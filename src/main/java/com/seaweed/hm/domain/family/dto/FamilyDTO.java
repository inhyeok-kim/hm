package com.seaweed.hm.domain.family.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDTO {
    private long id;
    private String name;
    private String inviteCode;

}
