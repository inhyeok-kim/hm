package com.seaweed.hm.application.item;

import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.domain.item.dto.ItemCategoryDTO;
import com.seaweed.hm.domain.item.entity.ItemCategory;
import com.seaweed.hm.domain.item.repository.ItemCategoryRepository;
import com.seaweed.hm.domain.item.repository.query.ItemCategoryQueryRepository;
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
public class ItemCategoryTest {
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ItemCategoryQueryRepository itemCategoryQueryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testRootList() throws UnAuthorizationException {
        User user = userRepository.findById(1L).get();
        List<ItemCategoryDTO> list =  itemCategoryQueryRepository.findRootByFamilyIdAndOrder(1,1);

        System.out.println(list);
    }

    @Test
    void testChildreList() {
        User user = userRepository.findById(1L).get();
        List<ItemCategoryDTO> list =  itemCategoryQueryRepository.findByParentIdAndOrder(1,1);

        System.out.println(list);

    }

}
