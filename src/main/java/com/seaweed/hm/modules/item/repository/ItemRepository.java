package com.seaweed.hm.modules.item.repository;

import com.seaweed.hm.modules.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findListByClassId(long classId);

}
