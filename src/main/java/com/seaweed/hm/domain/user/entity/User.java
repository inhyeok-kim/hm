package com.seaweed.hm.domain.user.entity;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
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

    @Column(name="family_id")
    private long familyId;

    public boolean hasFamily(){
        return this.familyId > 0;
    }

    @Builder
    public User(String name, String uid, String email, String phoneNumber){
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


}