package com.seaweed.hm.modules.family.enums;

import com.seaweed.hm.modules.item.exception.NotFoundTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FamilyJoinStatus {
    WAIT("대기","1"),
    APPROVE("승인","2"),
    DENY("거절","3");

    private String code;
    private String name;
    FamilyJoinStatus(String name, String i) {
        this.name = name;
        this.code = i;
    }

    public static FamilyJoinStatus ofValue(String i) {
        return Arrays.stream(FamilyJoinStatus.values()).filter(familyJoinStatus -> familyJoinStatus.getCode().equals(i)).findAny().orElseThrow(() -> new NotFoundTypeException("해당하는 유형이 없습니다."));
    }

}
