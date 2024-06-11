package com.seaweed.hm.domain.user.service;

import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.domain.family.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void joinFamily(User user, Family family) {
        userRepository.updateFamily(user.getId(), family.getId());
    }
}
