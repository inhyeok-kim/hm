package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemClassTypeConverter;
import com.seaweed.hm.modules.item.enums.ItemType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name="itemClass")
@Table(name = "ITEM_CLASS")
@Data
@NoArgsConstructor
@ToString
public class ItemClass extends DefaultEntity {
    private String name;
    private long familyId;

    @Convert(converter = ItemClassTypeConverter.class)
    private ItemClassType type;

    @OneToMany(mappedBy = "itemClass")
    private List<Item> items = new ArrayList<>();

    @Builder
    public ItemClass(long createUserId,String name, long familyId, ItemClassType type){
        System.out.println("createUserId : " + createUserId);
        this.name = name;
        this.familyId = familyId;
        this.createUserId = createUserId;
        this.type = type;
    }

    public ItemClass modifyName(String name){
        this.name = name;
        return this;
    }
    public ItemClass modifyType(ItemClassType type){
        this.type = type;
        return this;
    }

    public ItemClass modifyFamily(long familyId){
        this.familyId = familyId;
        return this;
    }
}