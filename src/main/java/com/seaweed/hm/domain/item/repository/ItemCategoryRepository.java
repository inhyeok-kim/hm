package com.seaweed.hm.domain.item.repository;

import com.seaweed.hm.domain.item.entity.Item;
import com.seaweed.hm.domain.item.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
}
