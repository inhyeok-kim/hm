package com.seaweed.hm.modules.family.domain.model.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatusConverter;
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
    public FamilyJoin(long familyId, long userId){
        this.familyId = familyId;
        this.createUserId = userId;
        this.userId = userId;
        this.status = FamilyJoinStatus.WAIT;
    }

}
