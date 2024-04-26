package com.seaweed.hm.modules.auth.usecase;

import com.seaweed.hm.modules.auth.dto.SimpleAuthDTO;
import com.seaweed.hm.modules.auth.entity.SimpleAuth;
import com.seaweed.hm.modules.auth.service.SimpleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleAuthUsecase {

    @Autowired
    private SimpleAuthService authService;

    public SimpleAuthDTO login(String loginId, String password) throws Exception {
        SimpleAuth loginAuth = authService.authenticate(loginId,SimpleAuth.encryptPassword(password));

        return loginAuth != null ? new SimpleAuthDTO(loginAuth) : null;
    }


}
