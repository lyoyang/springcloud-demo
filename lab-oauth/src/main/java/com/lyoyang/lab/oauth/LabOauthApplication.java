package com.lyoyang.lab.oauth;

import com.lyoyang.lab.common.annotation.EnableLabAuthExceptionHandler;
import com.lyoyang.lab.common.annotation.EnableLabServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableLabAuthExceptionHandler
@EnableLabServerProtect
public class LabOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabOauthApplication.class, args);
    }

}
