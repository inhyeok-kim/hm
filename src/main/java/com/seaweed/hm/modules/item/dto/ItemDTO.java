package com.seaweed.hm.modules.item.dto;

import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO{
    private long id;
    private String name;
    private long familyId;
    private int count;
    private ItemType type;
    private ItemClassType classType;
    private String thumbnail;

    public ItemDTO(Item entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
        this.classType = entity.getClassType();
        this.count = entity.getCount();
        this.type = entity.getType();
        this.thumbnail = entity.getThumbnail();
    }
}
