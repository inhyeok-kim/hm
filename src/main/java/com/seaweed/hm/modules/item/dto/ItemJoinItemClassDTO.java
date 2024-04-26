package com.seaweed.hm.modules.item.dto;

import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.enums.ItemType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemJoinItemClassDTO {
    private long id;
    private String name;
    private long classId;
    private int count;
    private ItemType type;
    private ItemClassDTO itemClass;

    public ItemJoinItemClassDTO(Item entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.classId = entity.getClassId();
        this.count = entity.getCount();
        this.type = entity.getType();
        this.itemClass = new ItemClassDTO(entity.getItemClass());
    }
}
