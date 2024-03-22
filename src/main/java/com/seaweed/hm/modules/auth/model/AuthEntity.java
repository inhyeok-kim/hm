package com.seaweed.hm.modules.auth.model;

import com.seaweed.hm.common.entity.DefaultEntity;
import com.seaweed.hm.modules.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HM_AUTH")
@NoArgsConstructor
@Data
public class AuthEntity extends DefaultEntity {

    private String loginId;
    private String password;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;
}
