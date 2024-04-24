package com.seaweed.hm.modules.auth.repository;

import com.seaweed.hm.modules.auth.entity.SimpleAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<SimpleAuthEntity,Long> {

    SimpleAuthEntity findByLoginIdAndPassword(String loginId, String password);
}
