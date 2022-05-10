package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class DemoUtil {

    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密
    // MD5加密的两个特点：1.只能加密不能解密 2.初始密码加上随机字符串后进行加密
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

}
