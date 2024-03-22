package com.seaweed.hm;

import com.seaweed.hm.modules.auth.model.AuthRegistDTO;
import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.user.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.NoSuchAlgorithmException;

@SpringBootTest(properties = "spring.profiles.active:local")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
public class AuthTests {

    @Autowired
    AuthService authService;

    @Test
    void testRegist() throws NoSuchAlgorithmException {
        AuthRegistDTO authRegistDTO = new AuthRegistDTO("test","테스터",authService.encryptPassword("1234"));
        authService.registUser(authRegistDTO);
    }

    @Test
    void testLogin() throws NoSuchAlgorithmException {
        UserDTO loginUser = authService.login("test",authService.encryptPassword("1234"));
        System.out.println(loginUser);
    }
}
