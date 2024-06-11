package com.seaweed.hm.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SimpleUserRegistDTO {
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;
    private long familyId;
}
