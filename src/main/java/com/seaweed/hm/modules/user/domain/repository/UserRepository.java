package com.seaweed.hm.modules.user.domain.repository;

import com.seaweed.hm.modules.user.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUid(String uid);

    @Modifying
    @Query("UPDATE user SET familyId = :familyId WHERE id = :id")
    void updateFamily(@Param("id")long id, @Param("familyId") long familyId);
}
