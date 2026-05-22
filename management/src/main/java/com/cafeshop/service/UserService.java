package com.cafeshop.service;

import com.cafeshop.Login.LoginInfo;
import com.cafeshop.Login.User;

public interface UserService {

    LoginInfo refreshToken(String refreshToken);

    LoginInfo login(User user);

}
