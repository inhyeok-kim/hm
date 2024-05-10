package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemClassTypeConverter;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.enums.ItemTypeConverter;
import com.seaweed.hm.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="item")
@Table(name = "ITEM")
@NoArgsConstructor
@ToString
@Getter
public class Item extends DefaultEntity {
    private String name;
    private long familyId;
    @Column(name = "cnt")
    private int count;

    @Convert(converter = ItemClassTypeConverter.class)
    private ItemClassType classType;
    
    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;

    @Builder(builderMethodName = "registBuilder")
    public Item(long createUserId,String name, long familyId, ItemType type, ItemClassType classType,int count){
        this.name = name;
        this.familyId = familyId;
        this.createUserId = createUserId;
        this.type = type;
        this.count = count;
        this.classType = classType;
    }

    public Item modifyName(long updateUserId, String name){
        this.lastModifyUserId = updateUserId;
        this.name = name;
        return this;
    }
    public Item modifyType(long updateUserId, ItemType type){
        this.lastModifyUserId = updateUserId;
        this.type = type;
        return this;
    }

    public Item modifyFamily(long updateUserId, long familyId){
        this.lastModifyUserId = updateUserId;
        this.familyId = familyId;
        return this;
    }

    public Item modifyClassType(long updateUserId, ItemClassType classType){
        this.lastModifyUserId = updateUserId;
        this.classType = classType;
        return this;
    }

    public Item modifyCount(long updateUserId, int count){
        this.lastModifyUserId = updateUserId;
        this.count = count;
        return this;
    }

    public Item plusCount(long updateUserId, int count){
        this.count += count;
        this.lastModifyUserId = updateUserId;
        return this;
    }

    public boolean isAccessible(User user){
        return user.getFamilyId() == this.familyId;
    }
}
