package com.seaweed.hm.modules.auth.repository;

import com.seaweed.hm.modules.auth.model.AuthEntity;
import com.seaweed.hm.modules.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity,Long> {

    AuthEntity findByLoginIdAndPassword(String loginId, String password);
}
