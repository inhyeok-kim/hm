package com.seaweed.hm.modules.auth.dto;

import com.seaweed.hm.modules.auth.entity.SimpleAuth;
import lombok.Data;

@Data
public class SimpleAuthDTO {
    private long id;
    private String loginId;
    private long userId;

    public SimpleAuthDTO(SimpleAuth entity){
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.userId = entity.getUserId();
    }

}
