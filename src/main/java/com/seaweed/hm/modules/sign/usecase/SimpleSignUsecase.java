package com.seaweed.hm.modules.sign.usecase;

import com.seaweed.hm.modules.auth.model.SimpleAuth;
import com.seaweed.hm.modules.auth.service.SimpleAuthService;
import com.seaweed.hm.modules.message.event.SimpleMessageEvent;
import com.seaweed.hm.modules.user.model.SimpleUser;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
public class SimpleSignUsecase {

    @Autowired
    private SimpleUserService simpleUserService;

    @Autowired
    private SimpleAuthService simpleAuthService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void sign(String loginId, String password, String name) throws NoSuchAlgorithmException {
        long userId = simpleUserService.registNewUser(
                SimpleUser.builder()
                        .uid(loginId)
                        .name(name)
                        .build()
        );
        SimpleAuth newAuth = SimpleAuth.builder()
                .loginId(loginId)
                .userId(userId)
                .password(password)
                .build();
        simpleAuthService.regist(newAuth);
        applicationEventPublisher.publishEvent(new SimpleMessageEvent("신규 사용자 가입 완료 | 아이디 : "+loginId+"이름 : "+name));
    }
}
