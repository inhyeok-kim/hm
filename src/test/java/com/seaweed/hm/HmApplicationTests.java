package com.seaweed.hm;

import com.seaweed.hm.modules.family.FamilyEntity;
import com.seaweed.hm.modules.family.FamilyRepository;
import com.seaweed.hm.modules.user.UserEntity;
import com.seaweed.hm.modules.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "spring.profiles.active:local")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
class HmApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FamilyRepository familyRepository;

	@Test
	void saveUserFamily() {
		UserEntity userEntity = new UserEntity("이름","아이디","비번");
		userRepository.save(userEntity);
		FamilyEntity familyEntity = new FamilyEntity("가족");
		familyRepository.save(familyEntity);
	}

	@Test
	void findUser(){
		userRepository.findAll().forEach(userEntity -> {
			FamilyEntity fa = userEntity.getFamily();
			System.out.println(fa);
		});
	}

	@Test
	void findFamily(){
		familyRepository.findAll().forEach(familyEntity -> System.out.println(familyEntity.getId()));
	}

	@Test
	void joinUserWithFamily(){
		UserEntity userEntity = userRepository.findAll().get(0);
		FamilyEntity familyEntity = familyRepository.findAll().get(0);
		userEntity.setFamily(familyEntity);
		userRepository.save(userEntity);
	}

	@Test
	void userFamilyTest(){
		saveUserFamily();
		joinUserWithFamily();
	}

}
