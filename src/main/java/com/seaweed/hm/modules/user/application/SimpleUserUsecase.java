package com.seaweed.hm.modules.user.application;

import com.seaweed.hm.comm.abstracts.service.DefaultService;
import com.seaweed.hm.modules.user.domain.model.dto.SimpleUserDTO;
import com.seaweed.hm.modules.user.domain.model.entity.User;
import com.seaweed.hm.modules.user.domain.service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserUsecase extends DefaultService {

    @Autowired
    private SimpleUserService simpleUserService;

    public SimpleUserDTO findUserById(long id){
        User user = simpleUserService.getUserById(id);
        if(user == null) return null;
        return new SimpleUserDTO(user);
    }


}
