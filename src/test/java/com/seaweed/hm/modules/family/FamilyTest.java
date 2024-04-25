package com.seaweed.hm.modules.family;

import com.seaweed.hm.modules.family.entity.FamilyEntity;
import com.seaweed.hm.modules.family.model.FamilyDTO;
import com.seaweed.hm.modules.family.repository.FamilyRepository;
import com.seaweed.hm.modules.family.service.FamilyService;
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
public class FamilyTest {

    @Autowired
    private FamilyService familyService;

    @Test
    void test(){
        List<FamilyDTO> list = familyService.getFamilyListAll();
        System.out.println(list);
    }

}
