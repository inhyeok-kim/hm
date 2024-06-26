package com.seaweed.hm.modules.item.repository;

import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findListByFamilyIdAndClassType(long familyId, ItemClassType classType, Pageable pageable);

    List<Item> findByFamilyIdAndNameContainingIgnoreCase(long familyId, String name);
}
