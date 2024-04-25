package com.seaweed.hm.modules.family.model;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.family.entity.FamilyEntity;
import com.seaweed.hm.modules.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Family {

    private long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String name;
    private List<Long> userIds;

    public Family(FamilyEntity entity){
        this.id = entity.getId();
        this.createAt = entity.getCreateAt();
        this.modifiedAt = entity.getModifiedAt();
        this.name = entity.getName();
        this.userIds = entity.getUsers().stream().map(DefaultEntity::getId).toList();
    }

    public boolean containsUser(long id){
        return this.userIds.contains(id);
    }

}
