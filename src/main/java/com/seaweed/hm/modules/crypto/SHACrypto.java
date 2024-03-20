package com.seaweed.hm.modules.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHACrypto {
    public static String encrypt(String text, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update((text+salt).getBytes());
        byte[] pwdsalt = md.digest();

        StringBuffer sb = new StringBuffer();
        for (byte b : pwdsalt) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
