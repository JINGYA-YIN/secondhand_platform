package com.example.secondhand_platform.model;

import java.util.Date;

public class User {
    private Integer id;
    private String username; // 账号（手机号/邮箱）
    private String password; // 加密密码
    private String salt;     // 盐值
    private String nickname; // 昵称
    private Date createTime; // 注册时间

    public User() {}

    public User(Integer id, String username, String password, String salt, String nickname, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.nickname = nickname;
        this.createTime = createTime;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
