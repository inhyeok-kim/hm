package com.seaweed.hm.modules.family;

import com.seaweed.hm.common.entity.DefaultEntity;
import com.seaweed.hm.modules.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
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

    public FamilyEntity(String name){
        this.name = name;
    }
}
