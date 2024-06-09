package com.seaweed.hm.modules.family.domain.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.family.domain.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.enums.FamilyJoinStatusConverter;
import com.seaweed.hm.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="familyJoinReq")
@Table(name = "FAMILY_JOIN_REQ")
@Getter
@NoArgsConstructor
@ToString
public class FamilyJoinReq extends DefaultEntity {
    private long familyId;
    private long userId;
    @Convert(converter = FamilyJoinStatusConverter.class)
    private FamilyJoinStatus status;

    @ManyToOne(targetEntity = Family.class)
    @JoinColumn(name = "familyId", insertable = false, updatable = false, foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
    private Family family;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId", insertable = false, updatable = false, foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Builder(builderMethodName = "NewRequest")
    public FamilyJoinReq(long familyId, long userId){
        this.familyId = familyId;
        this.createUserId = userId;
        this.userId = userId;
        this.status = FamilyJoinStatus.WAIT;
    }

}
