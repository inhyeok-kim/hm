package com.seaweed.hm.domain.family.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class FamilyMemberVO {
    private long id;
    private String name;
}
