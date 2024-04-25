package com.seaweed.hm.modules.family.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name="family")
@Table(name = "HM_FAMILY")
@Data
@NoArgsConstructor
@ToString
public class FamilyEntity extends DefaultEntity {
    private String name;

    @OneToMany(mappedBy = "family")
    private List<UserEntity> users = new ArrayList<>();

}
