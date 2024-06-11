package com.seaweed.hm.application.item.query;

import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.domain.item.dto.ItemDTO;
import com.seaweed.hm.domain.item.enums.ItemClassType;
import com.seaweed.hm.domain.item.repository.query.ItemQueryRepository;
import com.seaweed.hm.domain.item.repository.ItemRepository;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.domain.user.service.UserDomainService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemQueryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private ItemQueryRepository itemQueryRepository;
    @Autowired
    private ItemRepository itemRepository;

    /**
     * 사용자 family의 item 목록을 가져오는 usecase
     * @param loginId
     * @return
     * @throws NotFoundException 소속 가족이 없는 경우
     */
    public List<ItemDTO> getItemListOfFamily(long loginId, ItemClassType classType, Pageable pageable) throws NotFoundException, NoSuchMethodException {
        Optional<User> userOpt = userRepository.findById(loginId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");
        User user = userOpt.get();

        List<ItemDTO> pageList = itemQueryRepository.findListByFamilyIdAndClassType(user.getFamilyId(), classType, pageable);

        return pageList;
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
        Optional<User> userOpt = userRepository.findById(loginId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");
        User user = userOpt.get();

        ItemDTO item = itemQueryRepository.findByIdAndFamilyId(itemId, user.getFamilyId());
        return item;
    }

    public List<ItemDTO> searchItem(long loginId, String keyword) throws NotFoundException {
        Optional<User> userOpt = userRepository.findById(loginId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");
        User user = userOpt.get();

        List<ItemDTO> itemList = itemQueryRepository.findByFamilyIdAndNameKeyword(user.getFamilyId(), keyword);
        return itemList;
    }

}
