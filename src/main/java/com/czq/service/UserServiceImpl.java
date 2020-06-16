package com.czq.service;

import com.czq.dao.UserMapper;
import com.czq.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper)
    {
        this.userMapper=userMapper;
    }

    public User queryUserByUsername(String username, String password) {
        return userMapper.queryUserByUsername(username,password);
    }

    public User queryUser(@Param("username") String username) {
        return userMapper.queryUser(username);
    }

    public String queryPassword(String username, String password) {
        userMapper.queryPassword(username,password);
        return "修改成功，请登录！！！！";
    }

    public String addUser(String username, String password, String phone, String email) {
        userMapper.addUser(username,password,phone,email);
        return "注册成功，请登录！！！";
    }
}
