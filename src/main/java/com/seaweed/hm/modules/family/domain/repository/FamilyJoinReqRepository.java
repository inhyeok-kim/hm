package com.seaweed.hm.modules.family.domain.repository;

import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.model.entity.FamilyJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyJoinReqRepository extends JpaRepository<FamilyJoin,Long> {

    List<FamilyJoin> findAllByUserIdAndStatus(long userId, FamilyJoinStatus status);

    List<FamilyJoin> findAllByFamilyIdAndStatus(long familyId, FamilyJoinStatus status);
}
