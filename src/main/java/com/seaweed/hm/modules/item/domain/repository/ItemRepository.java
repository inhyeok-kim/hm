package com.seaweed.hm.modules.item.domain.repository;

import com.seaweed.hm.modules.item.domain.model.entity.Item;
import com.seaweed.hm.modules.item.domain.model.enums.ItemClassType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByFamilyIdAndNameContainingIgnoreCase(long familyId, String name);
}
