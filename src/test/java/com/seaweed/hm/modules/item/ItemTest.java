package com.seaweed.hm.modules.item;

import com.seaweed.hm.modules.item.service.ItemClassService;
import com.seaweed.hm.modules.item.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ItemTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemClassService itemClassService;

    @Test
    void test(){
        System.out.println(itemService.getItemList(1));
    }

    @Test
    void classTest(){
        System.out.println(itemClassService.getItemClassListAll());
    }
}
