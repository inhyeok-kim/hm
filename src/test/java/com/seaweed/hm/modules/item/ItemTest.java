package com.seaweed.hm.modules.item;

import com.seaweed.hm.comm.component.collections.PageList;
import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.application.ItemQueryService;
import com.seaweed.hm.modules.item.domain.model.enums.ItemClassType;
import com.seaweed.hm.modules.item.domain.dto.ItemDTO;
import com.seaweed.hm.modules.item.domain.model.enums.ItemType;
import com.seaweed.hm.modules.item.application.ItemService;
import com.seaweed.hm.modules.item.domain.service.ItemDomainService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ItemTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemQueryService itemQueryService;


    @Test
    @DisplayName("Item 생성 usecase 테스트")
    void createItemTest() throws UnAuthorizationException, NotFoundException {
        ItemDTO dto = new ItemDTO();
        dto.setType(ItemType.CONSUMABLES);
        dto.setFamilyId(1);
        dto.setClassType(ItemClassType.FOOD);
        dto.setName("멸치칼국수");
        dto.setCount(8);
        ItemDTO item = itemService.createItem(1,dto);
        Assertions.assertEquals(dto.getName(),itemQueryService.getItem(1,item.getId()).getName());
    }

    //@Test
    @DisplayName("Item 수정 usecase 테스트")
    void updateItemTest() throws UnAuthorizationException, NotFoundException {
        ItemDTO dto = itemQueryService.getItem(1,1);
        dto.setName("휴지수정");
        dto.setCount(3);
        ItemDTO result = itemService.updateItem(1,dto);
        Assertions.assertEquals(dto.getName(),result.getName());
    }
    @Test
    @DisplayName("Item 삭제 usecase 테스트")
    void deleteItemTest() throws UnAuthorizationException, NotFoundException {
        ItemDTO dto = itemQueryService.getItem(1,1);
        itemService.deleteItem(1,1);
        Assertions.assertNull(itemQueryService.getItem(1,1));
    }

    @Test
    void selectItemTest() throws NotFoundException, NoSuchMethodException {
        List<ItemDTO> list = itemQueryService.getItemListOfFamily(1, ItemClassType.FOOD, Pageable.ofSize(2).withPage(0));

        System.out.println(list);
    }

    @Test
    void searchTest(){
        List<ItemDTO> itemList = itemQueryService.searchItem(1,"만두");
        System.out.println(itemList);
    }

    @Test
    void getItemTest() throws UnAuthorizationException, NotFoundException {
        ItemDTO item = itemQueryService.getItem(1,1);
        System.out.println(item);
    }
}
