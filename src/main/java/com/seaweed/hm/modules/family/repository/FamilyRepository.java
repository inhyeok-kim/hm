package com.seaweed.hm.modules.family.repository;

import com.seaweed.hm.modules.family.model.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity,Long> {

}
