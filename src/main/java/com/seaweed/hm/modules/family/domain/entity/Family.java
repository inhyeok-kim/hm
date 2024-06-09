package com.seaweed.hm.modules.family.domain.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name="family")
@Table(name = "FAMILY")
@Getter
@NoArgsConstructor
@ToString
public class Family extends DefaultEntity {
    private String name;

    @OneToMany(mappedBy = "family")
    private List<User> users = new ArrayList<>();

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

    public boolean containsUser(long id){
        return this.users.stream().anyMatch(user -> user.getFamilyId() == id);
    }

}
