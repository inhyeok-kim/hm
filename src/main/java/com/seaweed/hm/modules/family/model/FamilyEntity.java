package com.seaweed.hm.modules.family.model;

import com.seaweed.hm.common.entity.DefaultEntity;
import com.seaweed.hm.modules.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HM_FAMILY")
@Data
@NoArgsConstructor
@ToString
public class FamilyEntity extends DefaultEntity {
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    private List<UserEntity> userList = new ArrayList<>();
    public FamilyEntity(String name){
        this.name = name;
    }


}
