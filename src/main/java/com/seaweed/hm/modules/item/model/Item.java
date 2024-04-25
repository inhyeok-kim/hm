package com.seaweed.hm.modules.item.model;

import com.seaweed.hm.comm.abstracts.model.DefaultDomain;
import com.seaweed.hm.modules.item.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Item extends DefaultDomain {
    private String name;
    private long classId;
    private int count;
    private ItemType type;
}
