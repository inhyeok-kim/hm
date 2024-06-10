package com.seaweed.hm.modules.item.domain.dto;

import com.seaweed.hm.modules.item.domain.model.entity.Item;
import com.seaweed.hm.modules.item.domain.model.enums.ItemClassType;
import com.seaweed.hm.modules.item.domain.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
