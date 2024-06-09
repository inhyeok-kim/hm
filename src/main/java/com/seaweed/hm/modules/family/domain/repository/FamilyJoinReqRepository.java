package com.seaweed.hm.modules.family.domain.repository;

import com.seaweed.hm.modules.family.domain.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.domain.entity.FamilyJoinReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyJoinReqRepository extends JpaRepository<FamilyJoinReq,Long> {

    List<FamilyJoinReq> findAllByUserIdAndStatus(long userId, FamilyJoinStatus status);

    List<FamilyJoinReq> findAllByFamilyIdAndStatus(long familyId, FamilyJoinStatus status);
}
