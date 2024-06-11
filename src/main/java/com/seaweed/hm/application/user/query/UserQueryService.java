package com.seaweed.hm.application.user.query;

import com.seaweed.hm.abstracts.service.DefaultService;
import com.seaweed.hm.domain.user.dto.UserDTO;
import com.seaweed.hm.domain.user.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService extends DefaultService {

    @Autowired
    private UserQueryRepository userQueryRepository;

    public UserDTO findUserById(long id){
        return userQueryRepository.findById(id);
    }

}
