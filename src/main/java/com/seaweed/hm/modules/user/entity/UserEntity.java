package com.seaweed.hm.modules.user.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "SIMPLE_USER")
@Data
@NoArgsConstructor
@ToString
public class UserEntity extends DefaultEntity {
    private String name;
    private String uid;
    private String email;
    private String phoneNumber;
    private long familyId;

    public UserEntity(String name, String uid){
        this.name = name;
        this.uid = uid;
    }

}