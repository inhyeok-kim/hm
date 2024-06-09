package com.seaweed.hm.modules.item.domain.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ItemTypeConverter implements AttributeConverter<ItemType,String> {

    @Override
    public String convertToDatabaseColumn(ItemType itemClassType) {
        if(itemClassType == null) return null;
        return itemClassType.getCode();
    }

    @Override
    public ItemType convertToEntityAttribute(String i) {
        return ItemType.ofValue(i);
    }
}
