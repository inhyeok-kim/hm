package com.seaweed.hm.modules.item.repository;

import com.seaweed.hm.modules.item.entity.ItemClassEntity;
import com.seaweed.hm.modules.item.entity.ItemEntity;
import com.seaweed.hm.modules.item.model.ItemClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemClassRepository extends JpaRepository<ItemClassEntity,Long> {

    @Query("SELECT c,i FROM itemClass c LEFT JOIN FETCH c.items i")
    List<ItemClassEntity> findAllJoinItem();
}
