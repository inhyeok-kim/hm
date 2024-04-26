package com.seaweed.hm.modules.auth.entity;

import com.seaweed.hm.comm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.comm.util.crypto.SHACryptoUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "SIMPLE_AUTH")
@NoArgsConstructor
@Getter
public class SimpleAuth extends DefaultEntity {

    private String loginId;
    private String password;
    private long userId;

    private static final String PASSWORD_SALT = "simple_password";

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        return SHACryptoUtil.encrypt(password,PASSWORD_SALT);
    }

    @Builder
    public SimpleAuth(String loginId,String password,long userId) throws NoSuchAlgorithmException {
        this.loginId = loginId;
        this.password = encryptPassword(password);
        this.userId = userId;
    }

}
