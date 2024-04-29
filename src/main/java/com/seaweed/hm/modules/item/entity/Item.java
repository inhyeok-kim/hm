package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemClassType;
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
    @Column(name = "class_id")
    private long classId;
    private int count;

    @ManyToOne(targetEntity = ItemClass.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", insertable = false, updatable = false, foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
    private ItemClass itemClass;
    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;

    @Builder
    public Item(String name, long classId, int count, ItemType type){
        this.name = name;
        this.classId = classId;
        this.count = count;
        this.type = type;
    }

    @Builder(builderMethodName = "registBuilder")
    public Item(long createUserId,String name, long classId, ItemType type, int count){
        this.name = name;
        this.classId = classId;
        this.createUserId = createUserId;
        this.type = type;
        this.count = count;
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

    public Item modifyClass(long updateUserId, long classId){
        this.lastModifyUserId = updateUserId;
        this.classId = classId;
        return this;
    }

    public Item modifyCount(long updateUserId, int count){
        this.lastModifyUserId = updateUserId;
        this.count = count;
        return this;
    }

    public boolean isAccessible(User user){
        return user.getFamilyId() == this.itemClass.getFamilyId();
    }
}
