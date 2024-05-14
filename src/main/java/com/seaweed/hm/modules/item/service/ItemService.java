package com.seaweed.hm.modules.item.service;

import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.repository.ItemRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Page<Item> getItemListOfFamily(long familyId, Pageable pageable){
        return itemRepository.findListByFamilyId(familyId, pageable);
    }

    public Item regist(Item item) {
        return itemRepository.save(item);
    }

    public Item modify(Item item) throws NotFoundException {
        if(itemRepository.findById(item.getId()).isPresent()){
            return itemRepository.save(item);
        } else {
            throw new NotFoundException("");
        }
    }

    public void remove(Item item) {
        itemRepository.delete(item);
    }

    public Item getItem(long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }
}
