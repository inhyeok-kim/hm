package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.enums.ItemTypeConverter;
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
}
