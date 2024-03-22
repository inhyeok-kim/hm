package com.seaweed.hm.modules.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegistDTO{
    private String loginId;
    private String name;
    private String password;
}
