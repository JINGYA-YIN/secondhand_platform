package com.example.secondhand_platform.service;

import com.example.secondhand_platform.dao.UserDAO;
import com.example.secondhand_platform.dao.impl.UserDAOimpl;
import com.example.secondhand_platform.model.User;
import com.example.secondhand_platform.util.PasswordUtil;

public class UserService {
    private UserDAO userDAO = new UserDAOimpl();

    /**
     * 注册用户。
     * @return 0: 注册成功; 1: 账号已存在; 2: 数据库插入失败(其他错误)
     */
    public int register(String username, String password, String nickname) {
        // 1. 检查账号是否已存在
        if (userDAO.findByUsername(username) != null) {
            return 1; // 账号已存在
        }

        // 2. 加密密码
        String salt = PasswordUtil.generateSalt();
        String encryptPwd = PasswordUtil.encryptPassword(password, salt);

        // 3. 封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPwd);
        user.setSalt(salt);
        user.setNickname(nickname);

        // 4. 执行注册（插入数据库）
        int rows = userDAO.register(user);

        if (rows > 0) {
            return 0; // 注册成功
        } else {
            // 这通常是数据库连接失败、SQL语法错误等问题
            return 2; // 数据库插入失败 (其他错误)
        }
    }

    // 登录
    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return null;
        }
        // 检查密码
        if (PasswordUtil.checkPassword(password, user.getPassword(), user.getSalt())) {
            return user;
        }
        return null;
    }
}