package com.seaweed.hm.modules.item.domain.model.enums;

import com.seaweed.hm.modules.item.presentaion.exception.NotFoundTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ItemType {
    CONSUMABLES("소모품","1"),
    PERMANENT("반영구","2");

    private String code;
    private String name;
    ItemType(String name, String i) {
        this.name = name;
        this.code = i;
    }

    public static ItemType ofValue(String i) {
        return Arrays.stream(ItemType.values()).filter(itemType -> itemType.getCode().equals(i)).findAny().orElseThrow(() -> new NotFoundTypeException("해당하는 유형이 없습니다."));
    }

}
