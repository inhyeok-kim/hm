package com.seaweed.hm.modules.family.domain.repository;

import com.seaweed.hm.modules.family.domain.model.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {

    @Query("SELECT f FROM family f")
    List<Family> findFamilyJoinUser();

    @Modifying
    @Query("UPDATE family SET inviteCode = :inviteCode WHERE id = :id")
    void updateInviteCode(@Param("id") long id, @Param("inviteCode") String inviteCode);

    Optional<Family> findByInviteCode(String inviteCode);
}
