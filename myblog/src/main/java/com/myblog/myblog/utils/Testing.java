package com.myblog.myblog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Testing {
    public static void main(String[] args) {


        PasswordEncoder encoder= new BCryptPasswordEncoder();
        System.out.println(encoder.encode("vamsikrshina"));
    }

}
