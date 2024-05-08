package com.seaweed.hm.modules.user.service;

import com.seaweed.hm.comm.abstracts.service.DefaultService;
import com.seaweed.hm.modules.user.entity.User;
import com.seaweed.hm.modules.user.exception.DuplicateUserUidException;
import com.seaweed.hm.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleUserService extends DefaultService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserListAll(){
        return userRepository.findAll();
    }


    public User getUserById(long id){
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.orElse(null);
    }

    /**
     * 사용자 추가를 위한 service
     * @param user
     * @return
     */
    @Transactional
    public long registNewUser(User user) throws DuplicateUserUidException{
        Optional<User> checkUser = userRepository.findByUid(user.getUid());
        if(checkUser.isPresent()){
            throw new DuplicateUserUidException("중복된 사용자 아이디");
        }
        User newUser = userRepository.save(user);

        return newUser.getId();
    }


    @Transactional
    public void updateUserFamily(User user) {
        userRepository.updateFamily(user.getId(), user.getFamilyId());
    }
}
