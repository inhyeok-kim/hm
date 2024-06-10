package com.seaweed.hm.modules.item.application;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.domain.dto.ItemDTO;
import com.seaweed.hm.modules.item.domain.model.entity.Item;
import com.seaweed.hm.modules.item.domain.repository.ItemQueryRepository;
import com.seaweed.hm.modules.item.domain.repository.ItemRepository;
import com.seaweed.hm.modules.item.domain.service.ItemDomainService;
import com.seaweed.hm.modules.user.domain.model.entity.User;
import com.seaweed.hm.modules.user.domain.repository.UserRepository;
import com.seaweed.hm.modules.user.domain.service.SimpleUserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemDomainService itemDomainService;

    @Autowired
    private SimpleUserService simpleUserService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemQueryRepository itemQueryRepository;

    /**
     * item을 신규 생성하는 usecase
     * @param userId
     * @param dto
     * @return
     * @throws UnAuthorizationException 사용자가 소속 가족이 없을 경우
     */
    public ItemDTO createItem(long userId, ItemDTO dto) throws UnAuthorizationException {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) throw new UnAuthorizationException("존재하지 않는 대상입니다.");

        User user = userOpt.get();

        Item item = Item.registBuilder()
                .familyId(user.getFamilyId())
                .createUser(user)
                .type(dto.getType())
                .count(dto.getCount())
                .name(dto.getName())
                .classType(dto.getClassType())
                .thumbnail(dto.getThumbnail())
                .build();
        Item newItemClass = itemRepository.save(item);
        return new ItemDTO(newItemClass);
    }

    /**
     * item을 수정하는 usecase
     * family는 수정할 수 없다.
     * @param userId
     * @param itemDTO
     * @return ItemDTO
     * @throws UnAuthorizationException 수정 대상 itemClass가 사용자의 소속 가족이 아닐경우
     * @throws NotFoundException 수정 대상이 존재하지 않을 경우
     */
    public ItemDTO updateItem(long userId, ItemDTO itemDTO) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        Optional<Item> itemOpt = itemRepository.findById(itemDTO.getId());
        if(itemOpt.isEmpty()) throw new NotFoundException("존재하지 않는 대상입니다.");

        Item item = itemOpt.get();
        item.modifyName(user,itemDTO.getName())
                .modifyType(user,itemDTO.getType())
                .modifyClassType(user,itemDTO.getClassType())
                .modifyCount(user,itemDTO.getCount())
                .modifyThumbnail(user, itemDTO.getThumbnail());

        return new ItemDTO(item);
    }


    /**
     * item을 삭제하는 usecase
     * @param userId
     * @param itemId
     * @throws UnAuthorizationException 삭제 대상 itemClass가 사용자의 소속 가족이 아닐경우
     * @throws NotFoundException 삭제 대상이 존재하지 않을 경우
     */
    public void deleteItem(long userId, long itemId) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if(itemOpt.isEmpty()) throw new NotFoundException("존재하지 않는 대상입니다.");
        Item item = itemOpt.get();
        itemDomainService.remove(user,item);
    }

    public ItemDTO countPlusItem(long userId, long itemId, int count) throws UnAuthorizationException, NotFoundException {
        User user = simpleUserService.getUserById(userId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if(itemOpt.isEmpty()) throw new NotFoundException("존재하지 않는 대상입니다.");
        Item item = itemOpt.get();
        item.plusCount(user,count);

        return itemQueryRepository.findByIdAndFamilyId(item.getId(), item.getFamilyId());
    }

}
