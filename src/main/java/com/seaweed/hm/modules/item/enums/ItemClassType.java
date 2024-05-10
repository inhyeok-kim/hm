package com.seaweed.hm.modules.item.enums;

import com.seaweed.hm.modules.item.exception.NotFoundTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ItemClassType {
    LIVING("생활용품","1"),
    CONSUMABLES("소모품","2"),
    FURNITURE("가구가전","3"),
    FOOD("식료품","4");

    private String code;
    private String name;
    ItemClassType(String name,String i) {
        this.name = name;
        this.code = i;
    }

    public static ItemClassType ofValue(String i) {
        return Arrays.stream(ItemClassType.values()).filter(itemClassType -> itemClassType.getCode().equals(i)).findAny().orElseThrow(() -> new NotFoundTypeException("해당하는 유형이 없습니다."));
    }

}
