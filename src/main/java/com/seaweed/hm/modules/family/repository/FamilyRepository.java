package com.seaweed.hm.modules.family.repository;

import com.seaweed.hm.modules.family.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {

    @Query("SELECT f, u FROM family f LEFT JOIN FETCH f.users u")
    List<Family> findFamilyJoinUser();
}
