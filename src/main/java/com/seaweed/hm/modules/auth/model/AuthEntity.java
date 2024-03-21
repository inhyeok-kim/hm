package com.seaweed.hm.modules.auth.model;

import com.seaweed.hm.common.entity.DefaultEntity;
import com.seaweed.hm.modules.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HM_AUTH")
@NoArgsConstructor
public class AuthEntity extends DefaultEntity {

    private String loginId;
    private String password;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;
}
