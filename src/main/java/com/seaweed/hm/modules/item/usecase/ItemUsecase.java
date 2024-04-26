package com.seaweed.hm.modules.item.usecase;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.service.ItemClassService;
import com.seaweed.hm.modules.item.service.ItemService;
import com.seaweed.hm.modules.user.entity.User;
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

    /**
     * 사용자의 가족에 등록된 itemClass 목록을 가져오는 usecase
     * @param userId
     * @return
     */
    public List<ItemClassDTO> getItemClassListOfMyFamily(long userId){
        User user = simpleUserService.getUserById(userId);
        return itemClassService.getItemClassListByFamilyId(user.getFamilyId()).stream().map(itemClass -> new ItemClassDTO(itemClass)).toList();
    }

    /**
     * 사용자 가족의 itemClass를 신규 생성하는 usecase
     * @param userId
     * @param name
     * @param type
     * @return UnAuthorizationException : 사용자의 소속된 가족이 없을 경우
     *
     */
    public long createItemClass(long userId, String name, ItemClassType type) throws UnAuthorizationException {
        User user = simpleUserService.getUserById(userId);
        if(user.getFamilyId() == 0) throw new UnAuthorizationException("");
        ItemClass itemClass = ItemClass.builder().familyId(user.getFamilyId()).createUserId(userId).type(type).name(name).build();
        long itemClassId = itemClassService.regist(itemClass);
        return itemClassId;
    }

    /**
     * 사용자 가족의 itemClass를 수정하는 usecase
     * @param userId
     * @param itemClassDTO
     * @return UnAuthorizationException : 수정 대상 itemClass가 사용자의 소속 가족이 아닐경우
     */
    public ItemClassDTO updateItemClass(long userId, ItemClassDTO itemClassDTO) throws UnAuthorizationException {
        User user = simpleUserService.getUserById(userId);
        ItemClass itemClass = itemClassService.getItemClass(itemClassDTO.getId());
        if(user.getFamilyId() != itemClass.getFamilyId()) throw new UnAuthorizationException("");
        itemClass.modifyName(itemClassDTO.getName())
                .modifyType(itemClassDTO.getType())
                .modifyFamily(itemClassDTO.getFamilyId());
        return new ItemClassDTO(itemClassService.modify(itemClass));
    }

    /**
     * 사용자 가족의 itemClass를 삭제하는 usecase
     */

    /**
     * 사용자 가족의 item을 신규 생성하는 usecase
     */

    /**
     * 사용자 가족의 item을 신규 생성하는 usecase
     */

    /**
     * 사용자 가족의 item을 신규 생성하는 usecase
     */
}
