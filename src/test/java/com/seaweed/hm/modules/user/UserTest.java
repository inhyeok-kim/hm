package com.seaweed.hm.modules.user;

import com.seaweed.hm.modules.user.domain.model.dto.SimpleUserDTO;
import com.seaweed.hm.modules.user.domain.service.SimpleUserService;
import com.seaweed.hm.modules.user.application.SimpleUserUsecase;
import org.junit.jupiter.api.Assertions;
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
public class UserTest {

    @Autowired
    private SimpleUserService simpleUserService;

    @Autowired
    private SimpleUserUsecase simpleUserUsecase;

    @Test
    void test(){
        List<SimpleUserDTO> list = simpleUserService.getUserListAll().stream().map(SimpleUserDTO::new).toList();
        System.out.println(list);
    }

    @Test
    void testFindUser(){
        SimpleUserDTO user = simpleUserUsecase.findUserById(1);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }
}
