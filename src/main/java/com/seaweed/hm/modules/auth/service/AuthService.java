package com.seaweed.hm.modules.auth.service;

import com.seaweed.hm.common.service.DefaultService;
import com.seaweed.hm.modules.auth.model.AuthEntity;
import com.seaweed.hm.modules.auth.model.AuthRegistDTO;
import com.seaweed.hm.modules.auth.repository.AuthRepository;
import com.seaweed.hm.modules.crypto.RSACrypto;
import com.seaweed.hm.modules.crypto.SHACrypto;
import com.seaweed.hm.modules.user.model.UserDTO;
import com.seaweed.hm.modules.user.model.UserEntity;
import com.seaweed.hm.modules.user.repository.UserRepository;
import com.seaweed.hm.modules.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
public class AuthService extends DefaultService {
    public static String SESSION_LOGIN_NAME = "loginUser";
    private String PASSWORD_SALT = "hm_password";
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    @Transactional
    public UserDTO registUser(AuthRegistDTO registUser){
        UserDTO signUser = new UserDTO();
        signUser.setName(registUser.getName());
        signUser.setUid(registUser.getLoginId());
        UserDTO newUser = userService.registUser(signUser);

        Optional<UserEntity> user = userRepository.findById(newUser.getId());
        if(user.isPresent()){
            AuthEntity auth = new AuthEntity();
            auth.setPassword(registUser.getPassword());
            auth.setLoginId(registUser.getLoginId());
            auth.setUser(user.get());
            authRepository.save(auth);
            return new UserDTO(user.get());
        }

        return null;

    }


    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        return SHACrypto.encrypt(password,PASSWORD_SALT);
    }

    public UserDTO login(String loginId, String password){
        AuthEntity entity = authRepository.findByLoginIdAndPassword(loginId,password);
        if(entity != null){
            return new UserDTO(entity.getUser());
        } else {
            return null;
        }
    }
}
