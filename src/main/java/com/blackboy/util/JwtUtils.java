package com.blackboy.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import java.security.Key;

@Component
public class JwtUtils {

    @Value("${jwt.secret})")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    //模拟生成一个密钥（仅用于测试）
    public String mockNewSecret() throws NoSuchAlgorithmException {
        // 创建一个 HMAC - SHA 256 的 KeyGenerator 实例
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        // 初始化 KeyGenerator，生成 256 位的密钥
        keyGenerator.init(256);
        // 生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥的字节数组
        byte[] keyBytes = secretKey.getEncoded();
        // 将字节数组转换为 Base64 编码的字符串
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyBytes);
        return base64EncodedKey;
    }

    //生成密钥
    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //生成token
    public String generateToken(String username){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    //验证token
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //获取用户信息从token中
    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }




}
