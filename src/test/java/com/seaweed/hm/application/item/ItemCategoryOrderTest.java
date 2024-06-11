package com.seaweed.hm.application.item;

import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.domain.item.entity.ItemCategory;
import com.seaweed.hm.domain.item.entity.ItemCategoryOrder;
import com.seaweed.hm.domain.item.repository.ItemCategoryOrderRepository;
import com.seaweed.hm.domain.item.repository.ItemCategoryRepository;
import com.seaweed.hm.domain.item.vo.ItemCategoryOrderID;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ItemCategoryOrderTest {
    @Autowired
    private ItemCategoryOrderRepository itemCategoryOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() throws UnAuthorizationException {
        ItemCategoryOrder itemCategoryOrder = ItemCategoryOrder.builder().orderNum(1).categoryId(1).userId(1).build();
        itemCategoryOrderRepository.save(itemCategoryOrder);
        List<ItemCategoryOrder> list =  itemCategoryOrderRepository.findAll();
        System.out.println(list);
        ItemCategoryOrder order = itemCategoryOrderRepository.findById(new ItemCategoryOrderID(1,1)).get();
        System.out.println(order);

    }

}
