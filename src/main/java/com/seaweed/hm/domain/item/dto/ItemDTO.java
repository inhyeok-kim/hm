package com.seaweed.hm.domain.item.dto;

import com.seaweed.hm.domain.item.entity.Item;
import com.seaweed.hm.domain.item.enums.ItemClassType;
import com.seaweed.hm.domain.item.enums.ItemType;
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
    private long categoryId;
    private String categoryName;

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
