package com.blackboy.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PasswordEncryptor {
    private static final Logger logger = LoggerFactory.getLogger(PasswordEncryptor.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // 把密码加密为SHA-256
    public String encryptPasswordForSHA(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(plainPassword.getBytes());
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("SHA-256 加密时出现错误", e);
            throw new RuntimeException(e);
        }
    }

    // 生成BCryptPasswordEncoder密码，对前端送过来的加密密码做二次加密
    public String encryptPasswordForBCrypt(String plainPassword) {
        return bCryptPasswordEncoder.encode(plainPassword);
    }

    // 检查前端密码进行二次加密码后与数据库中密码是否一致
    public boolean checkPassword(String plainPassword, String encryptPassword) {
        return bCryptPasswordEncoder.matches(plainPassword, encryptPassword);
    }

}