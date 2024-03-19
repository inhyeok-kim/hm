package com.seaweed.hm.modules.family;

import com.seaweed.hm.modules.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity,Long> {

}
