package com.seaweed.hm.domain.auth.entity;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.comm.util.crypto.SHACryptoUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Entity
@Table(name = "SIMPLE_AUTH")
@NoArgsConstructor
@Getter
public class Auth extends DefaultEntity {

    private String loginId;
    private String password;
    private long userId;

    private static final String PASSWORD_SALT = "simple_password";

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        return SHACryptoUtil.encrypt(password,PASSWORD_SALT);
    }

    @Builder
    public Auth(String loginId, String password, long userId) throws NoSuchAlgorithmException {
        this.loginId = loginId;
        this.password = encryptPassword(password);
        this.userId = userId;
    }

    public boolean veify(String password) throws NoSuchAlgorithmException {
        return Objects.equals(this.password, encryptPassword(password));
    }

}
