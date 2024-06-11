package com.seaweed.hm.application.user.command;

import com.seaweed.hm.application.auth.command.AuthService;
import com.seaweed.hm.abstracts.service.DefaultService;
import com.seaweed.hm.domain.auth.entity.Auth;
import com.seaweed.hm.infrastucture.message.SimpleMessageEvent;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.domain.user.service.UserDomainService;
import com.seaweed.hm.application.user.exception.DuplicateUserUidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService extends DefaultService {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

}
