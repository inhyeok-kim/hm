package com.seaweed.hm.modules.item.dto;

import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemClassJoinItemDTO {
    private long id;
    private String name;
    private long familyId;
    private ItemClassType type;
    private List<ItemDTO> items;

    public ItemClassJoinItemDTO(ItemClass entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.familyId = entity.getFamilyId();
        this.type = entity.getType();
        this.items = entity.getItems().stream().map(item -> new ItemDTO(item)).toList();
    }
}