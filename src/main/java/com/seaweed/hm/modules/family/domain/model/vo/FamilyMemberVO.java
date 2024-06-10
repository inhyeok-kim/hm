package com.seaweed.hm.modules.family.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class FamilyMemberVO {
    private long id;
    private String name;
}
