package com.seaweed.hm.modules.user.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.family.entity.FamilyEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity(name="user")
@Table(name = "SIMPLE_USER")
@Data
@NoArgsConstructor
@ToString
public class UserEntity extends DefaultEntity {
    private String name;
    private String uid;
    private String email;
    private String phoneNumber;

    @ManyToOne(targetEntity = FamilyEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", insertable = false, updatable = false, foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
    private FamilyEntity family;

    @Column(name="family_id")
    private long familyId;


}