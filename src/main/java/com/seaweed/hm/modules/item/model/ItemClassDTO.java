package com.seaweed.hm.modules.item.model;

import com.seaweed.hm.modules.item.entity.ItemClassEntity;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemClassDTO{
    private long id;
    private String name;
    private long familyId;
    private ItemClassType type;
    private List<ItemDTO> items;

    public ItemClassDTO(ItemClassEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
        this.type = entity.getType();
        this.items = entity.getItems().stream().map(itemEntity -> new ItemDTO(itemEntity)).toList();
    }

}