package com.cafeshop.mapper;

import com.cafeshop.Login.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 會員登入
     * @param username
     * @param password
     * @return
     */
    @Select("select id, username,password from users where username = #{username} and password = #{password}")
    User selectByUsernameAndPassWord(@Param("username") String username,
                                     @Param("password") String password);
}
