package com.seaweed.hm.domain.auth.dto;

import com.seaweed.hm.domain.auth.entity.Auth;
import lombok.Data;

@Data
public class AuthDTO {
    private long id;
    private String loginId;
    private long userId;

    public AuthDTO(Auth entity){
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.userId = entity.getUserId();
    }

}
