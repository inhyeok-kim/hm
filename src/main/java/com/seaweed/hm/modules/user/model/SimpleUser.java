package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.comm.abstracts.model.DefaultDomain;
import com.seaweed.hm.modules.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SimpleUser extends DefaultDomain {
    private String uid;
    private String name;
    private String email;
    private String phoneNumber;
    private long familyId;

    public SimpleUser(UserEntity entity){
        super.id = entity.getId();
        super.createAt = entity.getCreateAt();
        super.modifiedAt = entity.getModifiedAt();
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

    public boolean hasFamily(){
        return this.familyId > 0;
    }


}
