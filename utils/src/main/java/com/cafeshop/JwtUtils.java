package com.cafeshop;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 密鑰（一定要放工具類，不要寫在 Test）
    private static final SecretKey key = Keys.hmacShaKeyFor(
            "my-secret-key-my-secret-key-my-secret-key-123456".getBytes()
    );

    // 2小時過期
    public static final long ACCESS_EXP = 1000 * 60 * 60 * 2;
    // refresh token（長）
    public static final long REFRESH_EXP = 1000L * 60 * 60 * 24 * 7; // 7天

    /**
     * 產生 Token
     */
    public static String generateToken(Map<String, Object> claims) {
        return buildToken(claims, ACCESS_EXP);
    }

    /**
     * refresh token
     */
    public static String generateRefreshToken(Map<String, Object> claims) {
        return buildToken(claims, REFRESH_EXP);
    }

    private static String buildToken(Map<String, Object> claims, long exp) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 Token
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}