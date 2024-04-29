package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemClassTypeConverter;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="itemClass")
@Table(name = "ITEM_CLASS")
@Getter
@NoArgsConstructor
@ToString
public class ItemClass extends DefaultEntity {
    private String name;
    private long familyId;

    @Convert(converter = ItemClassTypeConverter.class)
    private ItemClassType type;

    @OneToMany(mappedBy = "itemClass")
    private List<Item> items = new ArrayList<>();

    @Builder(builderMethodName = "registBuilder")
    public ItemClass(long createUserId,String name, long familyId, ItemClassType type){
        this.name = name;
        this.familyId = familyId;
        this.createUserId = createUserId;
        this.type = type;
    }

    public ItemClass modifyName(long updateUserId, String name){
        this.lastModifyUserId = updateUserId;
        this.name = name;
        return this;
    }
    public ItemClass modifyType(long updateUserId, ItemClassType type){
        this.lastModifyUserId = updateUserId;
        this.type = type;
        return this;
    }

    public ItemClass modifyFamily(long updateUserId, long familyId){
        this.lastModifyUserId = updateUserId;
        this.familyId = familyId;
        return this;
    }

    public boolean isAccessible(User user){
        return this.familyId == user.getFamilyId();
    }
}