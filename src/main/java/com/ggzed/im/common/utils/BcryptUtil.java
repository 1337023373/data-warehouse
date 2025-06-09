package com.ggzed.im.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
/**
 * Bcryp密码加密
 * @author rikka
 * */
public class BcryptUtil {
 
    /**
     * 加密
     * @param password 明文密码
     * @return 加密后密码
     * */
    public static String doEncrypt(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        return encodePassword;
    }
 
    /**
     * 比较明文密码是否时加密后密码
     * @param password 铭文比较密码
     * @param encodePassword 加密密码
     * @return 是否匹配成功
     * */
    public static boolean matchPassword(String password,String encodePassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isTure = passwordEncoder.matches(password, encodePassword);
        return isTure;
    }
}