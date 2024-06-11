package com.seaweed.hm.application.sign.command;

import com.seaweed.hm.abstracts.service.DefaultService;
import com.seaweed.hm.application.auth.command.AuthService;
import com.seaweed.hm.application.user.exception.DuplicateUserUidException;
import com.seaweed.hm.domain.auth.entity.Auth;
import com.seaweed.hm.domain.auth.repository.AuthRepository;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import com.seaweed.hm.domain.user.service.UserDomainService;
import com.seaweed.hm.infrastucture.message.SimpleMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class SignService extends DefaultService {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Transactional
    public void sign(String loginId, String password, String name) throws NoSuchAlgorithmException, DuplicateUserUidException {
        Optional<User> checkUser = userRepository.findByUid(loginId);
        if(checkUser.isPresent()){
            throw new DuplicateUserUidException("중복된 사용자 아이디");
        }
        User newUser = User.builder()
                .uid(loginId)
                .name(name)
                .build();
        newUser = userRepository.save(newUser);

        Auth newAuth = Auth.builder()
                .loginId(loginId)
                .userId(newUser.getId())
                .password(password)
                .build();
        authRepository.save(newAuth);
        applicationEventPublisher.publishEvent(new SimpleMessageEvent("신규 사용자 가입 완료 | 아이디 : "+loginId+"이름 : "+name));
    }
}
