package com.seaweed.hm.modules.auth.usecase;

import com.seaweed.hm.modules.auth.model.SimpleAuth;
import com.seaweed.hm.modules.auth.service.SimpleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleAuthUsecase {

    @Autowired
    private SimpleAuthService authService;

    public SimpleAuth login(String loginId, String password) throws Exception {
        return authService.authenticate(loginId,SimpleAuth.encryptPassword(password));
    }


}
