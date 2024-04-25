package com.seaweed.hm.modules.item.usecase;

import com.seaweed.hm.modules.family.service.FamilyService;
import com.seaweed.hm.modules.item.model.ItemClassDTO;
import com.seaweed.hm.modules.item.service.ItemClassService;
import com.seaweed.hm.modules.item.service.ItemService;
import com.seaweed.hm.modules.user.model.SimpleUser;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemUsecase {

    @Autowired
    private ItemClassService itemClassService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SimpleUserService simpleUserService;

    public List<ItemClassDTO> getItemClassListOfMyFamily(long userId){
        SimpleUser user = simpleUserService.getUserById(userId);
        return itemClassService.getItemClassListByFamilyId(user.getFamilyId());
    }

}
