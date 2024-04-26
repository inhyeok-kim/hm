package com.seaweed.hm.modules.item.service;

import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItemList(long classId){
        return itemRepository.findListByClassId(classId);
    }

}
