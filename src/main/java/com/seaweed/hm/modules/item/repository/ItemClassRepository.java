package com.seaweed.hm.modules.item.repository;

import com.seaweed.hm.modules.item.entity.ItemClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemClassRepository extends JpaRepository<ItemClass,Long> {

    @Query("SELECT c FROM itemClass c LEFT JOIN FETCH c.items")
//    @EntityGraph(attributePaths = {"item"})
    List<ItemClass> findAllJoinItem();

    List<ItemClass> findByFamilyId(long familyId);
}
