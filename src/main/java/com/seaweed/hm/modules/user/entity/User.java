package com.seaweed.hm.modules.user.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.family.domain.entity.Family;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="user")
@Table(name = "SIMPLE_USER")
@NoArgsConstructor
@ToString
@Getter
public class User extends DefaultEntity {
    @NonNull
    private String name;
    @NonNull
    private String uid;
    private String email;
    private String phoneNumber;

    @ManyToOne(targetEntity = Family.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", insertable = false, updatable = false, foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
    private Family family;

    @Column(name="family_id")
    private long familyId;

    public boolean hasFamily(){
        return this.familyId > 0;
    }

    public void joinFamily(long familyId){
        this.familyId = familyId;
    }

    @Builder
    public User(String name, String uid, String email, String phoneNumber){
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


}