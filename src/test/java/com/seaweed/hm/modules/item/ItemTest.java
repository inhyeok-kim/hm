package com.seaweed.hm.modules.item;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.dto.ItemJoinItemClassDTO;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.repository.ItemClassRepository;
import com.seaweed.hm.modules.item.service.ItemClassService;
import com.seaweed.hm.modules.item.service.ItemService;
import com.seaweed.hm.modules.item.usecase.ItemUsecase;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ItemTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemClassService itemClassService;

    @Autowired
    private ItemUsecase itemUsecase;

    @Autowired
    private ItemClassRepository itemClassRepository;

    @Test
    @DisplayName("ItemClass 생성 usecase 테스트")
    void createClassTest() throws UnAuthorizationException {
        ItemClassDTO itemClass = itemUsecase.createItemClass(1,"맥주", ItemClassType.FOOD);
        Assertions.assertEquals(itemClass,new ItemClassDTO(itemClassService.getItemClass(itemClass.getId())));
    }

    @Test
    @DisplayName("ItemClass 수정 usecase 테스트")
    void updateClassTest() throws UnAuthorizationException, NotFoundException {
        ItemClassDTO dto = new ItemClassDTO(itemClassService.getItemClass(1));
        dto.setName("수정테스트");
        ItemClassDTO result = itemUsecase.updateItemClass(1,dto);
        Assertions.assertEquals(dto,result);
    }
    @Test
    @DisplayName("ItemClass 삭제 usecase 테스트")
    void deleteClassTest() throws UnAuthorizationException, NotFoundException {
        ItemClassDTO dto = new ItemClassDTO(itemClassService.getItemClass(1));
        itemUsecase.deleteItemClass(1,dto);
        Assertions.assertNull(itemClassService.getItemClass(1));
    }

    @Test
    @DisplayName("Item 생성 usecase 테스트")
    void createItemTest() throws UnAuthorizationException {
        ItemDTO item = itemUsecase.createItem(1,"멸치칼국수",8, ItemType.CONSUMABLES,10);
        Assertions.assertEquals(item,new ItemDTO(itemService.getItem(item.getId())));
    }

    @Test
    @DisplayName("Item 수정 usecase 테스트")
    void updateItemTest() throws UnAuthorizationException, NotFoundException {
        ItemDTO dto = new ItemDTO(itemService.getItem(1));
        dto.setName("휴지수정");
        dto.setCount(3);
        ItemDTO result = itemUsecase.updateItem(1,dto);
        Assertions.assertEquals(dto,result);
    }
    @Test
    @DisplayName("Item 삭제 usecase 테스트")
    void deleteItemTest() throws UnAuthorizationException, NotFoundException {
        ItemDTO dto = new ItemDTO(itemService.getItem(1));
        itemUsecase.deleteItem(1,dto);
        Assertions.assertNull(itemService.getItem(1));
    }
}
