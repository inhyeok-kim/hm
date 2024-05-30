package com.seaweed.hm.comm.util.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESCryptoUtil {

    private static final String algorithms = "AES/CBC/PKCS5Padding";

    public static String encrypt(String text, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
        IvParameterSpec iv = new IvParameterSpec("0123456789123456".getBytes());
        Cipher c = Cipher.getInstance(algorithms);

        c.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] encryptionByte = c.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptionByte);

    }
    public static String decrypt(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithms);

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec("0123456789123456".getBytes());

        // 암호화 적용
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        //암호 해석
        byte[] decodedBytes = Base64.getDecoder().decode(text);
        byte[] decrypted = cipher.doFinal(decodedBytes);

        return new String(decrypted, "UTF-8");

    }
}
