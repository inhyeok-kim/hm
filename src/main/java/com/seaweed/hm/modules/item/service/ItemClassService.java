package com.seaweed.hm.modules.item.service;

import com.seaweed.hm.modules.item.model.ItemClassDTO;
import com.seaweed.hm.modules.item.model.ItemDTO;
import com.seaweed.hm.modules.item.repository.ItemClassRepository;
import com.seaweed.hm.modules.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemClassService {

    @Autowired
    private ItemClassRepository itemClassRepository;

    public List<ItemClassDTO> getItemClassListAll(){
        return itemClassRepository.findAllJoinItem().stream().map(ItemClassDTO::new).toList();
    }

}
