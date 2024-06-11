package com.seaweed.hm.domain.auth.service;

import com.seaweed.hm.abstracts.service.DefaultService;
import com.seaweed.hm.domain.auth.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthDomainService extends DefaultService {
    @Autowired
    private AuthRepository authRepository;

}

