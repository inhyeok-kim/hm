package com.seaweed.hm.modules.user;

import com.seaweed.hm.common.entity.DefaultEntity;
import com.seaweed.hm.modules.family.FamilyEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "HM_USER")
@Data
@NoArgsConstructor
@ToString
public class UserEntity extends DefaultEntity {
    private String name;
    private String loginId;
    private String password;

    public UserEntity(String name, String loginId, String password){
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private FamilyEntity family;
}
