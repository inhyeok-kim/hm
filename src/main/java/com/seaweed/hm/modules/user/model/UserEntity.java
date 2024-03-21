package com.seaweed.hm.modules.user.model;

import com.seaweed.hm.common.entity.DefaultEntity;
import com.seaweed.hm.modules.family.model.FamilyEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "HM_USER")
@Data
@NoArgsConstructor
@ToString
public class UserEntity extends DefaultEntity {
    private String name;
    private String uid;

    public UserEntity(String name, String uid){
        this.name = name;
        this.uid = uid;
    }

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private FamilyEntity family;
}
