package com.seaweed.hm.modules.item.dto;

import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemClassDTO{
    private long id;
    private String name;
    private long familyId;
    private ItemClassType type;

    public ItemClassDTO(ItemClass entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
        this.type = entity.getType();
    }

}