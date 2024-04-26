package com.seaweed.hm.modules.item;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.dto.ItemJoinItemClassDTO;
import com.seaweed.hm.modules.item.repository.ItemClassRepository;
import com.seaweed.hm.modules.item.service.ItemClassService;
import com.seaweed.hm.modules.item.service.ItemService;
import com.seaweed.hm.modules.item.usecase.ItemUsecase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
    void test(){
        System.out.println(itemService.getItemList(1));
    }

    @Test
    @Transactional
    void classTest(){
        List itemList = itemService.getItemList(1).stream().map(item -> new ItemDTO(item)).toList();
        System.out.println(itemList);
        List joinItemList = itemService.getItemList(1).stream().map(item -> new ItemJoinItemClassDTO(item)).toList();
        System.out.println(joinItemList);

    }

    @Test
    void usecaseTest(){
        System.out.println(itemUsecase.getItemClassListOfMyFamily(1));
    }

    @Test
    void usecaseTest2() throws UnAuthorizationException {
        long classId = itemUsecase.createItemClass(1,"맥주", ItemClassType.FOOD);
        System.out.println(classId);
    }

    @Test
    void updateTest() throws UnAuthorizationException {
        ItemClassDTO dto = new ItemClassDTO(itemClassService.getItemClass(1));
        dto.setName("수정테스트");
        System.out.println(itemUsecase.updateItemClass(1,dto));
    }
}
