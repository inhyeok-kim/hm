package com.seaweed.hm.modules.item.model;

import com.seaweed.hm.comm.abstracts.model.DefaultDomain;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ItemClass extends DefaultDomain {
    private String name;
    private String familyId;

    private ItemClassType type;


}
