package com.seaweed.hm;

import com.seaweed.hm.modules.auth.model.AuthRegistDTO;
import com.seaweed.hm.modules.auth.service.AuthService;
import com.seaweed.hm.modules.family.model.FamilyEntity;
import com.seaweed.hm.modules.family.repository.FamilyRepository;
import com.seaweed.hm.modules.user.model.UserEntity;
import com.seaweed.hm.modules.user.repository.UserRepository;
import com.seaweed.hm.modules.user.service.UserService;
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
class HmApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FamilyRepository familyRepository;

	@Autowired
	AuthService authService;





}
