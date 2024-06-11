package com.seaweed.hm.application.auth.command;

import com.seaweed.hm.domain.auth.entity.Auth;
import com.seaweed.hm.domain.auth.repository.AuthRepository;
import com.seaweed.hm.domain.auth.service.AuthDomainService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthDomainService authService;

    @Autowired
    private AuthRepository authRepository;

    public long login(String loginId, String password) throws Exception {
        Optional<Auth> entity = authRepository.findByLoginId(loginId);
        if(entity.isEmpty()) throw new NotFoundException("존재하지 않는 계정 정보");
        Auth auth = entity.get();
        if(!auth.veify(password)) return 0;
        return auth.getId();
    }

}
