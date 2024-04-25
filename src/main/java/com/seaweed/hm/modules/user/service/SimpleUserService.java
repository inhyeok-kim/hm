package com.seaweed.hm.modules.user.service;

import com.seaweed.hm.comm.abstracts.service.DefaultService;
import com.seaweed.hm.modules.user.exception.DuplicateUserUidException;
import com.seaweed.hm.modules.user.model.SimpleUser;
import com.seaweed.hm.modules.user.entity.UserEntity;
import com.seaweed.hm.modules.user.model.SimpleUserDTO;
import com.seaweed.hm.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleUserService extends DefaultService {

    @Autowired
    private UserRepository userRepository;

    public List<SimpleUserDTO> getUserListAll(){
        return userRepository.findAll().stream().map(SimpleUserDTO::new).toList();
    }


    public SimpleUser getUserById(long id){
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            return new SimpleUser(userOpt.get());
        }
        return null;
    }

    /**
     * 사용자 추가를 위한 service
     * @param user
     * @return
     */
    public long registNewUser(SimpleUser user) throws DuplicateUserUidException{
        Optional<UserEntity> checkUser = userRepository.findByUid(user.getUid());
        if(checkUser.isPresent()){
            throw new DuplicateUserUidException("중복된 사용자 아이디");
        }
        UserEntity newUser = userRepository.save(user.toEntity());

        return newUser.getId();
    }

}
