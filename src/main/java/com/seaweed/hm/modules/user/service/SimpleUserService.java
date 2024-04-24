package com.seaweed.hm.modules.user.service;

import com.seaweed.hm.comm.abstracts.service.DefaultService;
import com.seaweed.hm.modules.user.model.SimpleUser;
import com.seaweed.hm.modules.user.model.SimpleUserRegistDto;
import com.seaweed.hm.modules.user.entity.UserEntity;
import com.seaweed.hm.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimpleUserService extends DefaultService {

    @Autowired
    private UserRepository userRepository;


    public SimpleUser getUserById(long id){
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            return new SimpleUser(userOpt.get());
        }
        return null;
    }

    public long registNewUser(SimpleUser user){
        UserEntity newUser = userRepository.save(user.toEntity());

        return newUser.getId();
    }

}
