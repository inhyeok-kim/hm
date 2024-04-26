package com.seaweed.hm.modules.auth.repository;

import com.seaweed.hm.modules.auth.entity.SimpleAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<SimpleAuth,Long> {

    Optional<SimpleAuth> findByLoginIdAndPassword(String loginId, String password);
}
