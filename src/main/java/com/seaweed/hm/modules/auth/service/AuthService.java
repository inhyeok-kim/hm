package com.seaweed.hm.modules.auth.service;

import com.seaweed.hm.common.service.DefaultService;
import com.seaweed.hm.modules.crypto.RSACrypto;
import com.seaweed.hm.modules.crypto.SHACrypto;
import com.seaweed.hm.modules.user.model.UserDTO;
import com.seaweed.hm.modules.user.model.UserEntity;
import com.seaweed.hm.modules.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
@Slf4j
public class AuthService extends DefaultService {
    public static String SESSION_LOGIN_NAME = "loginUser";
    private String PASSWORD_SALT = "hm_password";
    @Autowired
    private UserRepository userRepository;

    private String PUBLIC_KEY;
    private String PRIVATE_KEY;
    @PostConstruct
    public void init(){
        HashMap<String, String> keypairs = RSACrypto.createKeypairAsString();
        this.PUBLIC_KEY = keypairs.get("publicKey");
        this.PRIVATE_KEY = keypairs.get("privateKey");
        log.info("Auth :: RSA Crypto Key Created");
        log.info("Auth :: Public Key = " + PUBLIC_KEY);
        log.info("Auth :: Private Key = " + PRIVATE_KEY);
    }

    public void registUser(UserDTO registUser){
        userRepository.save(registUser.toEntity());
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        return SHACrypto.encrypt(password,PASSWORD_SALT);
    }

    public UserDTO login(String loginId, String password){
        UserEntity entity = userRepository.findByLoginIdAndPassword(loginId,password);
        if(entity != null){
            return new UserDTO(entity);
        } else {
            return null;
        }
    }
}
