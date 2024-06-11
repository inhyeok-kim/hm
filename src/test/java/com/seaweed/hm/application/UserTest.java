package com.seaweed.hm.application;

import com.seaweed.hm.application.user.query.UserQueryService;
import com.seaweed.hm.domain.user.dto.UserDTO;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.application.user.command.UserService;
import org.junit.jupiter.api.Assertions;
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
public class UserTest {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindUser(){
        UserDTO user = userQueryService.findUserById(1);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }
}
