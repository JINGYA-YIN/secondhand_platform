package com.example.secondhand_platform.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class PasswordUtil {
    // 生成随机盐值（UUID）
    public static String generateSalt() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // SHA-256哈希加密：密码+盐值拼接后加密
    public static String encryptPassword(String password, String salt) {
        try {
            // 拼接密码和盐值
            String raw = password + salt;
            // 初始化SHA-256加密算法
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 加密并转Base64
            byte[] digest = md.digest(raw.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不存在", e);
        }
    }

    // 验证密码：输入密码+盐值加密后对比存储的哈希值
    public static boolean checkPassword(String inputPwd, String storedHash, String salt) {
        String inputHash = encryptPassword(inputPwd, salt);
        return inputHash.equals(storedHash);
    }
}
