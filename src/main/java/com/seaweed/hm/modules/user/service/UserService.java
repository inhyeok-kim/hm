package com.seaweed.hm.modules.user.service;

import com.seaweed.hm.common.service.DefaultService;
import com.seaweed.hm.modules.user.model.UserDTO;
import com.seaweed.hm.modules.user.model.UserEntity;
import com.seaweed.hm.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO registUser(UserDTO user){
        UserEntity newUser = userRepository.save(user.toEntity());
        return new UserDTO(newUser);
    }

}
