package com.czq.service;

import com.czq.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User queryUserByUsername(String username, String password);

    String addUser(String username,
                   String password,
                   String phone,
                   String email);
    User queryUser(@Param("username") String username);

    String queryPassword(@Param("username") String username,
                         @Param("password") String password);
}
