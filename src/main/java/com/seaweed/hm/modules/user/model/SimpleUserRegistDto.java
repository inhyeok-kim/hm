package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.modules.user.entity.UserEntity;
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

    public UserEntity toEntity(){
        UserEntity entity = new UserEntity();
        entity.setUid(this.uid);
        entity.setEmail(this.email);
        entity.setName(this.name);
        entity.setFamilyId(this.familyId);
        entity.setPhoneNumber(this.phoneNumber);
        return entity;
    }
}
