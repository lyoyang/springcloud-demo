package com.lyoyang.lab.server.test;

import com.lyoyang.lab.common.annotation.LabCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
@LabCloudApplication
public class LabServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabServerTestApplication.class, args);
    }

}
