package com.seaweed.hm.modules.item.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemClassTypeConverter;
import jakarta.persistence.*;
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
public class ItemClassEntity extends DefaultEntity {
    private String name;
    private long familyId;

    @Convert(converter = ItemClassTypeConverter.class)
    private ItemClassType type;

    @OneToMany(mappedBy = "itemClass")
    private List<ItemEntity> items = new ArrayList<>();

}