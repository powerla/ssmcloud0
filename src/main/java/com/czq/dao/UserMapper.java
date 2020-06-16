package com.czq.dao;
import com.czq.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User queryUserByUsername(@Param("username") String username,
                             @Param("password") String password);
    void addUser(@Param("username") String username,
                 @Param("password") String password,
                 @Param("phone") String phone,
                 @Param("email") String email);

    User queryUser(@Param("username") String username);

    void queryPassword(@Param("username") String username,
                       @Param("password") String password);
}
