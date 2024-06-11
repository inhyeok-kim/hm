package com.seaweed.hm.domain.family.entity;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import com.seaweed.hm.domain.user.entity.User;
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
    public Family(User creator, String name) throws UserHasFamilyException {
        if(creator.hasFamily()) throw new UserHasFamilyException("이미 가입된 가족이 존재합니다.");
        this.name = name;
        this.createUserId = creator.getId();
        this.inviteCode = createInviteCode();
    }


    public String refreshInviteCode(){
        String code = createInviteCode();
        this.inviteCode = code;
        return code;
    }


    private String createInviteCode(){
//        String salt = "ivt-"+this.createUserId;
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
