package com.seaweed.hm.modules.auth.model;

import lombok.Data;

@Data
public class SimpleAuthDTO {
    private long id;
    private String loginId;
    private String password;
    private long userId;

}
