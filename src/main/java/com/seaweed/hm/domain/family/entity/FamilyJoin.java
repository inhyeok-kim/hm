package com.seaweed.hm.domain.family.entity;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.domain.family.enums.FamilyJoinStatusConverter;
import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="familyJoin")
@Table(name = "FAMILY_JOIN")
@Getter
@NoArgsConstructor
@ToString
public class FamilyJoin extends DefaultEntity {
    private long familyId;
    private long userId;
    @Convert(converter = FamilyJoinStatusConverter.class)
    private FamilyJoinStatus status;

    @Builder(builderMethodName = "NewRequest")
    public FamilyJoin(User user, Family family) throws UserHasFamilyException {
        if(user.hasFamily()) throw new UserHasFamilyException("이미 가입된 가족이 존재합니다.");
        this.familyId = family.getId();
        this.createUserId = user.getId();
        this.userId = user.getId();
        this.status = FamilyJoinStatus.WAIT;
    }

}
