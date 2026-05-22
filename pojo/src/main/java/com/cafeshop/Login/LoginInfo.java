package com.cafeshop.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Integer id;
    private String username;
    private String token;
    private Long expiredDate;
    private String refreshToken;
    private Long expiredDateR;
}
