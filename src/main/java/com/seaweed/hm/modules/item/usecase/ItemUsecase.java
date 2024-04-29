package com.seaweed.hm.modules.item.usecase;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.entity.Item;
import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.service.ItemClassService;
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
    private ItemClassService itemClassService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SimpleUserService simpleUserService;

    /**
     * 특정 itemClass를 조회하는 usecase
     * @param userId
     * @param itemClassId
     * @return ItemClassDTO
     * @throws UnAuthorizationException 사용자의 가족에 소속된 itemClass가 아닌경우
     * @throws NotFoundException 해당 itemClass가 존재하지 않는 경우
     */
    public ItemClassDTO getItemClass(long userId, long itemClassId) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        ItemClass itemClass = itemClassService.getItemClass(itemClassId);
        if(itemClass == null) throw new NotFoundException("");
        if(itemClass.isAccessible(user)){
            return new ItemClassDTO(itemClass);
        } else {
            throw new UnAuthorizationException("");
        }
    }

    /**
     * 사용자의 가족에 등록된 itemClass 목록을 가져오는 usecase
     * @param userId
     * @return List<ItemClassDTO>
     */
    public List<ItemClassDTO> getItemClassListOfUser(long userId){
        User user = simpleUserService.getUserById(userId);
        return itemClassService.getItemClassListByFamilyId(user.getFamilyId()).stream().map(itemClass -> new ItemClassDTO(itemClass)).toList();
    }

    /**
     * 사용자 가족의 itemClass를 신규 생성하는 usecase
     * @param userId
     * @param name
     * @param type
     * @return ItemClassDTO
     * @throws UnAuthorizationException : 사용자의 소속된 가족이 없을 경우
     */
    public ItemClassDTO createItemClass(long userId, String name, ItemClassType type) throws UnAuthorizationException {
        User user = simpleUserService.getUserById(userId);
        if(user.getFamilyId() == 0) throw new UnAuthorizationException("");
        ItemClass itemClass = ItemClass.registBuilder().familyId(user.getFamilyId()).createUserId(userId).type(type).name(name).build();
        ItemClass newItemClass = itemClassService.regist(itemClass);
        return new ItemClassDTO(newItemClass);
    }

    /**
     * 사용자 가족의 itemClass를 수정하는 usecase
     * @param userId
     * @param itemClassDTO
     * @return ItemClassDTO
     * @throws UnAuthorizationException 수정 대상 itemClass가 사용자의 소속 가족이 아닐경우
     * @throws NotFoundException 수정 대상이 존재하지 않을 경우
     */
    public ItemClassDTO updateItemClass(long userId, ItemClassDTO itemClassDTO) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        ItemClass itemClass = itemClassService.getItemClass(itemClassDTO.getId());
        if(itemClass == null) throw new NotFoundException("");

        if(!itemClass.isAccessible(user)) throw new UnAuthorizationException("");
        itemClass.modifyName(userId,itemClassDTO.getName())
                .modifyType(userId,itemClassDTO.getType())
                .modifyFamily(userId,itemClassDTO.getFamilyId());
        return new ItemClassDTO(itemClassService.modify(itemClass));
    }


    /**
     * 사용자 가족의 itemClass를 삭제하는 usecase
     * @param userId
     * @param itemClassDTO
     * @throws UnAuthorizationException 삭제 대상 itemClass가 사용자의 소속 가족이 아닐경우
     * @throws NotFoundException 삭제 대상이 존재하지 않을 경우
     */
    public void deleteItemClass(long userId, ItemClassDTO itemClassDTO) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        ItemClass itemClass = itemClassService.getItemClass(itemClassDTO.getId());
        if(itemClass == null) throw new NotFoundException("");

        if(!itemClass.isAccessible(user)) throw new UnAuthorizationException("");
        itemClassService.remove(itemClass);
    }

    /**
     * item을 신규 생성하는 usecase
     */
    public ItemDTO createItem(long userId, String name, long itemClassId,ItemType type, int count) throws UnAuthorizationException {
        User user = simpleUserService.getUserById(userId);
        if(user.getFamilyId() == 0) throw new UnAuthorizationException("");
        Item item = Item.registBuilder()
                .classId(user.getFamilyId())
                .createUserId(userId)
                .type(type)
                .count(count)
                .name(name).build();
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
                .modifyClass(userId,itemDTO.getClassId())
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
     * class에 해당하는 item 목록을 가져오는 usecase
     * @param loginId
     * @param classId
     * @return
     * @throws NotFoundException 해당하는 class가 없는 경우
     * @throws UnAuthorizationException 해당 class가 소속 가족이 다른경우
     */
    public List<ItemDTO> getItemListOfClass(long loginId, long classId) throws NotFoundException, UnAuthorizationException {
        User user = simpleUserService.getUserById(loginId);
        ItemClass itemClass = itemClassService.getItemClass(classId);
        if(itemClass == null) throw new NotFoundException("");
        if(itemClass.isAccessible(user)){
            return itemService.getItemList(classId).stream().map(ItemDTO::new).toList();
        } else {
            throw new UnAuthorizationException("");
        }
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
        if(item.getItemClass().isAccessible(user)){
            return new ItemDTO(itemService.getItem(itemId));
        } else {
            throw new UnAuthorizationException("");
        }
    }
}
