package com.seaweed.hm.modules.user.usecase;

import com.seaweed.hm.comm.abstracts.service.DefaultService;
import com.seaweed.hm.modules.user.model.SimpleUser;
import com.seaweed.hm.modules.user.model.SimpleUserDTO;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserUsecase extends DefaultService {

    @Autowired
    private SimpleUserService simpleUserService;

    public SimpleUserDTO findUserById(long id){
        return new SimpleUserDTO(simpleUserService.getUserById(id));
    }


}
