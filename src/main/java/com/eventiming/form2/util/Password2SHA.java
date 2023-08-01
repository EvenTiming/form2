package com.eventiming.form2.util;

import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Password2SHA {
    public String hashPassword(String password) {
        try {
            // 获取 SHA-256 摘要算法实例
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // 将密码转换为字节数组并进行摘要计算
            byte[] hash = messageDigest.digest(password.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果不支持 SHA-256 算法，则抛出异常
            e.printStackTrace();
        }

        return null;
    }

}
