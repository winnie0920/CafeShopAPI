package com.cafeshop.Login;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class User {
    private Integer id; //ID
    @JsonAlias({"username", "account", "loginId"})
    private String username; //帳號
    private String password; //密碼
    private String name; //姓名
    private Integer gender; //性别, 1:男, 2:女
    private String phone; //手機號碼
    private String image; //大頭貼
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //修改時間

}
