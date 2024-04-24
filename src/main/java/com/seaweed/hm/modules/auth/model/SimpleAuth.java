package com.seaweed.hm.modules.auth.model;


import com.seaweed.hm.comm.util.crypto.SHACryptoUtil;
import com.seaweed.hm.modules.auth.entity.SimpleAuthEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Getter
public class SimpleAuth {

    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String loginId;
    private String password;
    private long userId;
    private final String type = "simple";
    private static final String PASSWORD_SALT = "simple_password";

    @Builder
    public SimpleAuth(Long id, String loginId, String password, long userId) throws NoSuchAlgorithmException {
        this.id = id;
        this.loginId = loginId;
        this.password = encryptPassword(password);
        this.userId = userId;
    }


    public SimpleAuth(SimpleAuthEntity entity){
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.password = entity.getPassword();
        this.id = entity.getId();
        this.createAt = entity.getCreateAt();
        this.modifiedAt = entity.getModifiedAt();
    }

    public SimpleAuthEntity toEntity(){
        SimpleAuthEntity entity = new SimpleAuthEntity();
        entity.setLoginId(loginId);
        entity.setPassword(password);
        entity.setId(this.id);
        entity.setCreateAt(this.createAt);
        entity.setModifiedAt((this.modifiedAt));

        return entity;
    }

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        return SHACryptoUtil.encrypt(password,PASSWORD_SALT);
    }

}
