package com.seaweed.hm.modules.family.domain.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FamilyJoinStatusConverter implements AttributeConverter<FamilyJoinStatus,String> {

    @Override
    public String convertToDatabaseColumn(FamilyJoinStatus familyJoinStatus) {
        if(familyJoinStatus == null) return null;
        return familyJoinStatus.getCode();
    }

    @Override
    public FamilyJoinStatus convertToEntityAttribute(String i) {
        return FamilyJoinStatus.ofValue(i);
    }
}
