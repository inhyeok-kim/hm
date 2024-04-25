package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.enums.ItemTypeConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="item")
@Table(name = "ITEM")
@Data
@NoArgsConstructor
@ToString
public class ItemEntity extends DefaultEntity {
    private String name;
    @Column(name = "class_id")
    private long classId;
    private int count;

    @ManyToOne(targetEntity = ItemClassEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", insertable = false, updatable = false, foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
    private ItemClassEntity itemClass;
    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;
}
