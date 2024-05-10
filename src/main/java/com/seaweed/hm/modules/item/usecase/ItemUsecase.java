package com.seaweed.hm.modules.item.usecase;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.service.ItemService;
import com.seaweed.hm.modules.user.entity.User;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemUsecase {


    @Autowired
    private ItemService itemService;

    @Autowired
    private SimpleUserService simpleUserService;

    /**
     * item을 신규 생성하는 usecase
     * @param userId
     * @param dto
     * @return
     * @throws UnAuthorizationException 사용자가 소속 가족이 없을 경우
     */
    public ItemDTO createItem(long userId, ItemDTO dto) throws UnAuthorizationException {
        User user = simpleUserService.getUserById(userId);
        if(user.getFamilyId() == 0) throw new UnAuthorizationException("");
        Item item = Item.registBuilder()
                .familyId(user.getFamilyId())
                .createUserId(userId)
                .type(dto.getType())
                .count(dto.getCount())
                .name(dto.getName())
                .classType(dto.getClassType())
                .build();
        Item newItemClass = itemService.regist(item);
        return new ItemDTO(newItemClass);
    }

    /**
     * item을 수정하는 usecase
     * @param userId
     * @param itemDTO
     * @return ItemDTO
     * @throws UnAuthorizationException 수정 대상 itemClass가 사용자의 소속 가족이 아닐경우
     * @throws NotFoundException 수정 대상이 존재하지 않을 경우
     */
    public ItemDTO updateItem(long userId, ItemDTO itemDTO) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        Item item = itemService.getItem(itemDTO.getId());
        if(item == null) throw new NotFoundException("");

        if(!item.isAccessible(user)) throw new UnAuthorizationException("");
        item.modifyName(userId,itemDTO.getName())
                .modifyType(userId,itemDTO.getType())
                .modifyFamily(userId,itemDTO.getFamilyId())
                .modifyCount(userId,itemDTO.getCount());

        return new ItemDTO(itemService.modify(item));
    }


    /**
     * item을 삭제하는 usecase
     * @param userId
     * @param itemDTO
     * @throws UnAuthorizationException 삭제 대상 itemClass가 사용자의 소속 가족이 아닐경우
     * @throws NotFoundException 삭제 대상이 존재하지 않을 경우
     */
    public void deleteItem(long userId, ItemDTO itemDTO) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        Item item = itemService.getItem(itemDTO.getId());
        if(item == null) throw new NotFoundException("");

        if(!item.isAccessible(user)) throw new UnAuthorizationException("");
        itemService.remove(item);
    }

    /**
     * 사용자 family의 item 목록을 가져오는 usecase
     * @param loginId
     * @return
     * @throws NotFoundException 소속 가족이 없는 경우
     */
    public List<ItemDTO> getItemListOfFamily(long loginId) throws NotFoundException {
        User user = simpleUserService.getUserById(loginId);
        if(!user.hasFamily()) throw new NotFoundException("");

        return itemService.getItemListOfFamily(user.getFamilyId()).stream().map(ItemDTO::new).toList();
    }

    /**
     * 특정 id의 item을 가져오는 usecase
     * @param loginId
     * @param itemId
     * @return
     * @throws NotFoundException 해당하는 item이 없는 경우
     * @throws UnAuthorizationException 해당 item의 class가 소속 가족이 다른 경우
     */
    public ItemDTO getItem(long loginId, long itemId) throws NotFoundException, UnAuthorizationException {
        User user = simpleUserService.getUserById(loginId);
        Item item = itemService.getItem(itemId);
        if(item == null) throw new NotFoundException("");
        if(item.isAccessible(user)){
            return new ItemDTO(itemService.getItem(itemId));
        } else {
            throw new UnAuthorizationException("");
        }
    }
}
