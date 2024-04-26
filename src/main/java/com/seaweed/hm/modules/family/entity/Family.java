package com.seaweed.hm.modules.family.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="family")
@Table(name = "FAMILY")
@Getter
@NoArgsConstructor
@ToString
public class Family extends DefaultEntity {
    private String name;

    @OneToMany(mappedBy = "family")
    private List<User> users = new ArrayList<>();

    @Builder
    public Family(long createUserId,String name){
        this.name = name;
        this.createUserId = createUserId;
    }

    public boolean containsUser(long id){
        return this.users.stream().anyMatch(user -> user.getFamilyId() == id);
    }

}
