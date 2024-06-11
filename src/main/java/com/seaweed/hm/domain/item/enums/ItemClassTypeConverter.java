package com.seaweed.hm.domain.item.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ItemClassTypeConverter implements AttributeConverter<ItemClassType,String> {

    @Override
    public String convertToDatabaseColumn(ItemClassType itemClassType) {
        if(itemClassType == null) return null;
        return itemClassType.getCode();
    }

    @Override
    public ItemClassType convertToEntityAttribute(String i) {
        return ItemClassType.ofValue(i);
    }
}
