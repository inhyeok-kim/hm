package com.seaweed.hm.modules.auth.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SIMPLE_AUTH")
@NoArgsConstructor
@Data
public class SimpleAuthEntity extends DefaultEntity {

    private String loginId;
    private String password;
    private String userId;

}
