package com.seaweed.hm.domain.item.repository;

import com.seaweed.hm.domain.item.entity.Item;
import com.seaweed.hm.domain.item.entity.ItemCategoryOrder;
import com.seaweed.hm.domain.item.vo.ItemCategoryOrderID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCategoryOrderRepository extends JpaRepository<ItemCategoryOrder, ItemCategoryOrderID> {
}
