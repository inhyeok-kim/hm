package com.seaweed.hm.modules.family.domain.model.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity(name="family")
@Table(name = "FAMILY")
@Getter
@NoArgsConstructor
@ToString
public class Family extends DefaultEntity {
    private String name;

    private String inviteCode;

    @Builder
    public Family(long createUserId,String name){
        this.name = name;
        this.createUserId = createUserId;
        this.inviteCode = createInviteCode();
    }

    private String createInviteCode(){
//        String salt = "ivt-"+this.createUserId;
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public void refreshInviteCode(){
        this.inviteCode = createInviteCode();
    }

}
