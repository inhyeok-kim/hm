package com.seaweed.hm.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SimpleUserRegistDto {
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;
    private long familyId;

}
