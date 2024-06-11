package com.seaweed.hm.domain.item.repository;

import com.seaweed.hm.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByFamilyIdAndNameContainingIgnoreCase(long familyId, String name);
}
