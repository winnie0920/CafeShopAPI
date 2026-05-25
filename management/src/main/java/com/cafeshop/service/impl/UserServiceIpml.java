package com.cafeshop.service.impl;

import com.cafeshop.JwtUtils;
import com.cafeshop.Login.LoginInfo;
import com.cafeshop.Login.User;
import com.cafeshop.mapper.UserMapper;
import com.cafeshop.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class UserServiceIpml implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 會員登入
     * @param
     */
    @Override
    public LoginInfo login(User user) {
        User u = userMapper.selectByUsernameAndPassWord(
                user.getUsername(),
                user.getPassword()
        );
        if (u == null) {
            return null;
        }
        return buildToken(u);
    }

    /**
     * RefreshToken
     * @param refreshToken
     * @return
     */
    @Override
    public LoginInfo refreshToken(String refreshToken) {

        Claims claims = JwtUtils.parseToken(refreshToken);

        User u = new User();
        u.setId(Integer.valueOf(claims.get("id").toString()));
        u.setUsername(claims.get("username").toString());

        return buildToken(u);
    }

    /**
     * 製作Token
     * @param
     * @return
     */
    private LoginInfo buildToken(User u) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", u.getId());
        claims.put("username", u.getUsername());

        String accessToken = JwtUtils.generateToken(claims);
        String refreshToken = JwtUtils.generateRefreshToken(claims);

        Long expiredDate = System.currentTimeMillis() + JwtUtils.ACCESS_EXP;
        Long expiredDateR = System.currentTimeMillis() + JwtUtils.REFRESH_EXP;

        return new LoginInfo(
                u.getId(),
                u.getUsername(),
                accessToken,
                expiredDate,
                refreshToken,
                expiredDateR
        );
    }
}


