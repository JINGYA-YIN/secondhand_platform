package com.example.secondhand_platform.dao;

import com.example.secondhand_platform.model.User;

public interface UserDAO {
    // 注册用户
    int register(User user);
    // 根据账号查用户
    User findByUsername(String username);
}
