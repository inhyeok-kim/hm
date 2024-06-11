package com.seaweed.hm.domain.family.repository;

import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.domain.family.entity.FamilyJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyJoinRepository extends JpaRepository<FamilyJoin,Long> {

    List<FamilyJoin> findAllByUserIdAndStatus(long userId, FamilyJoinStatus status);

    List<FamilyJoin> findAllByFamilyIdAndStatus(long familyId, FamilyJoinStatus status);
}
