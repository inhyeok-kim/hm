package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.modules.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class SimpleUser {
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;
    private long familyId;

    public SimpleUser(UserEntity entity){
        this.id = entity.getId();
        this.createAt = entity.getCreateAt();
        this.modifiedAt = entity.getModifiedAt();
        this.uid = entity.getUid();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.familyId = entity.getFamilyId();
    }

    public UserEntity toEntity(){
        UserEntity entity = new UserEntity();
        entity.setId(this.id);
        entity.setUid(this.uid);
        entity.setName(this.name);
        entity.setPhoneNumber(this.phoneNumber);
        entity.setEmail(this.email);
        entity.setCreateAt(this.createAt);
        entity.setModifiedAt((this.modifiedAt));
        entity.setFamilyId(this.familyId);

        return entity;
    }



}
