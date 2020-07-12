package com.lyoyang.lab.oauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LabOauthApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void getPassword() {
        System.out.println(passwordEncoder.encode("123456"));
    }


}
