package com.cafeshop.controller;

import com.cafeshop.Login.LoginInfo;
import com.cafeshop.Result.Result;
import com.cafeshop.Result.ResultCode;
import com.cafeshop.Login.User;
import com.cafeshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 會員登入
     * @param
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登入：{}", user);
        LoginInfo info = userService.login(user);
        if (info != null) {
            return Result.success(info,"登入成功");
        }
        return Result.error(ResultCode.BAD_REQUEST,"用戶名密碼錯誤");
    }

    /**
     * RefreshToken
     * @param refreshToken
     * @return
     */
    @PostMapping("/refresh")
    public Result refresh(@RequestParam String refreshToken) {

        return Result.success(
                userService.refreshToken(refreshToken),"回傳reFreshToken成功"
        );
    }
}