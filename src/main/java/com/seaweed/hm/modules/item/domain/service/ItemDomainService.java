package com.seaweed.hm.modules.item.domain.service;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.domain.model.entity.Item;
import com.seaweed.hm.modules.item.domain.repository.ItemRepository;
import com.seaweed.hm.modules.user.domain.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDomainService {

    @Autowired
    private ItemRepository itemRepository;

    public void remove(User user, Item item) throws UnAuthorizationException {
        if(!item.isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        itemRepository.delete(item);
    }
}
